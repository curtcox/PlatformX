package org.robovm.objc;

import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.VM;
import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.Ptr;
import org.robovm.rt.bro.ptr.VoidPtr;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Library("Foundation")
@NativeClass("Object")
@Marshalers({    @org.robovm.rt.bro.annotation.Marshaler(ObjCObject.Marshaler.class),     @org.robovm.rt.bro.annotation.Marshaler(org.robovm.objc.ObjCClass.Marshaler.class)})
public abstract class ObjCObject extends NativeObject {
    private static volatile boolean logRetainRelease = false;
    static final Object objcBridgeLock;
    private static final LongMap<ObjCObject.ObjCObjectRef> peers;
    private static final long CUSTOM_CLASS_OFFSET;
    private static final long NS_OBJECT_CLASS;
    private ObjCSuper zuper;
    protected final boolean customClass;

    protected ObjCObject() {
        long handle = this.alloc();
        this.setHandle(handle);
        if(handle != 0L) {
            setPeerObject(handle, this);
        }

        this.customClass = this.getObjCClass().isCustom();
    }

    protected ObjCObject(long handle) {
        this.initObject(handle);
        this.customClass = this.getObjCClass().isCustom();
    }

    ObjCObject(long handle, boolean customClass) {
        this.initObject(handle);
        this.customClass = customClass;
    }

    protected void initObject(long handle) {
        if(handle == 0L) {
            throw new RuntimeException("Objective-C initialization method returned nil");
        } else {
            long oldHandle = this.getHandle();
            if(handle != oldHandle) {
                if(oldHandle != 0L) {
                    removePeerObject(this);
                }

                this.setHandle(handle);
                setPeerObject(handle, this);
            }

        }
    }

    protected long alloc() {
        throw new UnsupportedOperationException("Cannot create instances of " + this.getClass().getName());
    }

    protected final void finalize() throws Throwable {
        this.dispose(true);
    }

    public final void dispose() {
        this.dispose(false);
    }

    protected void doDispose() {
    }

    protected void dispose(boolean finalizing) {
        long handle = this.getHandle();
        if(handle != 0L) {
            removePeerObject(this);
            this.doDispose();
            this.setHandle(0L);
        }

        if(finalizing) {
            try {
                super.finalize();
            } catch (Throwable var5) {
                throw new RuntimeException(var5);
            }
        }

    }

    protected ObjCSuper getSuper() {
        if(this.zuper == null) {
            Class javaClass = this.getClass().getSuperclass();

            ObjCClass objCClass;
            for(objCClass = ObjCClass.getByType(javaClass); objCClass.isCustom(); objCClass = ObjCClass.getByType(javaClass)) {
                javaClass = javaClass.getSuperclass();
            }

            this.zuper = new ObjCSuper(this, objCClass);
        }

        return this.zuper;
    }

    protected void afterMarshaled(int flags) {
    }

    public final ObjCClass getObjCClass() {
        return ObjCClass.getFromObject(this);
    }

    static <T extends ObjCObject> T getPeerObject(long handle) {
        Object var2 = objcBridgeLock;
        synchronized(objcBridgeLock) {
            ObjCObject.ObjCObjectRef ref = (ObjCObject.ObjCObjectRef)peers.get(handle);
            ObjCObject o = ref != null?(ObjCObject)ref.get():null;
            return (T) o;
        }
    }

    private static void setPeerObject(long handle, ObjCObject o) {
        Object var3 = objcBridgeLock;
        synchronized(objcBridgeLock) {
            if(o == null) {
                peers.remove(handle);
            } else {
                peers.put(handle, new ObjCObject.ObjCObjectRef(o));
            }

        }
    }

    private static void removePeerObject(ObjCObject o) {
        Object var1 = objcBridgeLock;
        synchronized(objcBridgeLock) {
            long handle = o.getHandle();
            ObjCObject.ObjCObjectRef ref = (ObjCObject.ObjCObjectRef)peers.remove(handle);
            ObjCObject p = ref != null?(ObjCObject)ref.get():null;
            if(p != null && o != p) {
                peers.put(handle, new ObjCObject.ObjCObjectRef(p));
            }

        }
    }

    public <T> T addStrongRef(T to) {
        ObjCObject.AssociatedObjectHelper.addStrongRef(this, to);
        return to;
    }

    public void removeStrongRef(Object to) {
        ObjCObject.AssociatedObjectHelper.removeStrongRef(this, to, false);
    }

    public void updateStrongRef(Object before, Object after) {
        if(before != after) {
            if(before != null) {
                ObjCObject.AssociatedObjectHelper.removeStrongRef(this, before, true);
            }

            if(after != null) {
                ObjCObject.AssociatedObjectHelper.addStrongRef(this, after);
            }

        }
    }

    public Object getAssociatedObject(Object key) {
        return ObjCObject.AssociatedObjectHelper.getAssociatedObject(this, key);
    }

    public void setAssociatedObject(Object key, Object value) {
        ObjCObject.AssociatedObjectHelper.setAssociatedObject(this, key, value);
    }

    public static <T extends ObjCObject> T toObjCObject(Class<T> cls, long handle, int afterMarshaledFlags) {
        return toObjCObject(cls, handle, afterMarshaledFlags, false);
    }

    public static <T extends ObjCObject> T toObjCObject(Class<T> cls, long handle, int afterMarshaledFlags, boolean forceType) {
        if(handle == 0L) {
            return null;
        } else if(cls == ObjCClass.class) {
            return (T) ObjCClass.toObjCClass(handle);
        } else if(forceType) {
            return createInstance(ObjCClass.getByType(cls), handle, afterMarshaledFlags, false);
        } else {
            Class expectedType = cls;
            if(ObjCClass.isObjCProxy(cls)) {
                expectedType = cls.getInterfaces()[0];
            }

            Object var6 = objcBridgeLock;
            synchronized(objcBridgeLock) {
                ObjCObject o = getPeerObject(handle);
                if(o != null && o.getHandle() != 0L) {
                    if(expectedType.isAssignableFrom(o.getClass())) {
                        return (T) o;
                    }

                    if(!ObjCClass.isObjCProxy(o.getClass())) {
                        if(ObjCClass.isObjCProxy(cls)) {
                            return createInstance(ObjCClass.getByType(cls), handle, afterMarshaledFlags, false);
                        }

                        throw new IllegalStateException("The peer object type " + o.getClass().getName() + " is not compatible with the expected type " + expectedType.getName());
                    }

                    removePeerObject(o);
                    o = null;
                }

                ObjCClass objCClass = ObjCClass.getFromObject(handle);
                if(!expectedType.isAssignableFrom(objCClass.getType())) {
                    objCClass = ObjCClass.getByType(cls);
                }

                return createInstance(objCClass, handle, afterMarshaledFlags, true);
            }
        }
    }

    private static <T extends ObjCObject> T createInstance(ObjCClass objCClass, long handle, int afterMarshaledFlags, boolean makePeer) {
        Class c = objCClass.getType();
        ObjCObject o = (ObjCObject)VM.allocateObject(c);
        o.setHandle(handle);
        if(makePeer) {
            setPeerObject(handle, o);
        }

        if(objCClass.isCustom()) {
            VM.setBoolean(VM.getObjectAddress(o) + CUSTOM_CLASS_OFFSET, true);
        }

        o.afterMarshaled(afterMarshaledFlags);
        return (T) o;
    }

    public static void logRetainRelease(boolean enabled) {
        logRetainRelease = enabled;
    }

    private static void logRetainRelease(long cls, long self, int count, boolean isRetain) {
        String className = ObjCClass.getFromObject(cls).getType().getName();
        System.err.println(String.format("[Debug] %s %s@0x%s, retain count: %d", new Object[]{isRetain?"Retained":"Released", className, Long.toHexString(self), Integer.valueOf(isRetain?count + 1:count - 1)}));
    }

    static {
//        ObjCRuntime.bind();

//        try {
//            Field t = ObjCObject.class.getDeclaredField("customClass");
            CUSTOM_CLASS_OFFSET = 0;//(long)VM.getInstanceFieldOffset(VM.getFieldAddress(t));
//        } catch (Throwable var1) {
//            throw new Error(var1);
//        }

        NS_OBJECT_CLASS = 0;//ObjCRuntime.objc_getClass(VM.getStringUTFChars("NSObject"));
        objcBridgeLock = new Object();
        peers = new LongMap();
    }

    public static final class Super extends Struct<ObjCObject.Super> {
        public Super(long receiver, long objcClass) {
            this.receiver(receiver);
            this.objCClass(objcClass);
        }

        @StructMember(0)
        @Pointer
        public native long receiver();

        @StructMember(0)
        public native ObjCObject.Super receiver(@Pointer long var1);

        @StructMember(1)
        @Pointer
        public native long objCClass();

        @StructMember(1)
        public native ObjCObject.Super objCClass(@Pointer long var1);
    }

    static class AssociatedObjectHelper {
        private static final String STRONG_REFS_KEY = ObjCObject.AssociatedObjectHelper.class.getName() + ".StrongRefs";
        private static final int OBJC_ASSOCIATION_RETAIN_NONATOMIC = 1;
        private static final long RELEASE_LISTENER_CLASS;
        private static final String OWNER_IVAR_NAME = "value";
        private static final int OWNER_IVAR_OFFSET;
        private static final Selector alloc = Selector.register("alloc");
        private static final Selector init = Selector.register("init");
        private static final Selector release = Selector.register("release");
        private static final Selector retainCount = Selector.register("retainCount");
        private static final LongMap<Map<Object, Object>> ASSOCIATED_OBJECTS = new LongMap();

        AssociatedObjectHelper() {
        }

        private static void enableListener(long handle) {
            long releaseListener = ObjCRuntime.objc_getAssociatedObject(handle, RELEASE_LISTENER_CLASS);
            if(releaseListener == 0L) {
                releaseListener = ObjCRuntime.ptr_objc_msgSend(RELEASE_LISTENER_CLASS, alloc.getHandle());
                if(releaseListener == 0L) {
                    throw new OutOfMemoryError();
                }

                releaseListener = ObjCRuntime.ptr_objc_msgSend(releaseListener, init.getHandle());
                VM.setPointer(releaseListener + (long)OWNER_IVAR_OFFSET, handle);
                ObjCRuntime.objc_setAssociatedObject(handle, RELEASE_LISTENER_CLASS, releaseListener, 1);
                ObjCRuntime.void_objc_msgSend(releaseListener, release.getHandle());
            }

        }

        private static void disableListener(long handle) {
            ObjCRuntime.objc_setAssociatedObject(handle, RELEASE_LISTENER_CLASS, 0L, 0);
        }

        public static Object getAssociatedObject(ObjCObject object, Object key) {
            LongMap var2 = ASSOCIATED_OBJECTS;
            synchronized(ASSOCIATED_OBJECTS) {
                Map map = (Map)ASSOCIATED_OBJECTS.get(object.getHandle());
                return map == null?null:map.get(key);
            }
        }

        public static void setAssociatedObject(ObjCObject object, Object key, Object value) {
            LongMap var3 = ASSOCIATED_OBJECTS;
            synchronized(ASSOCIATED_OBJECTS) {
                Object map = (Map)ASSOCIATED_OBJECTS.get(object.getHandle());
                if(map != null || value != null) {
                    if(map == null) {
                        map = new HashMap();
                        enableListener(object.getHandle());
                        ASSOCIATED_OBJECTS.put(object.getHandle(), (Map<Object, Object>) map);
                    }

                    if(value != null) {
                        ((Map)map).put(key, value);
                    } else {
                        ((Map)map).remove(key);
                        if(((Map)map).isEmpty()) {
                            disableListener(object.getHandle());
                            ASSOCIATED_OBJECTS.remove(object.getHandle());
                        }
                    }

                }
            }
        }

        public static void addStrongRef(ObjCObject from, Object to) {
            if(to == null) {
                throw new NullPointerException();
            } else {
                LongMap var2 = ASSOCIATED_OBJECTS;
                synchronized(ASSOCIATED_OBJECTS) {
                    Object l = (List)getAssociatedObject(from, STRONG_REFS_KEY);
                    if(l == null) {
                        l = new ArrayList();
                        setAssociatedObject(from, STRONG_REFS_KEY, l);
                    }

                    ((List)l).add(to);
                }
            }
        }

        public static void removeStrongRef(ObjCObject from, Object to, boolean ignoreNotExists) {
            if(to == null) {
                throw new NullPointerException();
            } else {
                LongMap var3 = ASSOCIATED_OBJECTS;
                synchronized(ASSOCIATED_OBJECTS) {
                    List l = (List)getAssociatedObject(from, STRONG_REFS_KEY);
                    if(!ignoreNotExists && (l == null || !l.remove(to))) {
                        throw new IllegalArgumentException("No strong ref exists from " + from + " (a " + from.getClass().getName() + ") to " + to + " a (" + to.getClass().getName() + ")");
                    } else {
                        if(l != null && l.isEmpty()) {
                            setAssociatedObject(from, STRONG_REFS_KEY, (Object)null);
                        }

                    }
                }
            }
        }

        @Callback
        static void release(@Pointer long self, @Pointer long sel) {
            int count = ObjCRuntime.int_objc_msgSend(self, retainCount.getHandle());
            long cls;
            if(count == 1) {
                cls = VM.getPointer(self + (long)OWNER_IVAR_OFFSET);
                LongMap var7 = ASSOCIATED_OBJECTS;
                synchronized(ASSOCIATED_OBJECTS) {
                    ASSOCIATED_OBJECTS.remove(cls);
                }
            }

            if(ObjCObject.logRetainRelease) {
                cls = ObjCRuntime.object_getClass(self);
                //ObjCObject.access$100(cls, self, count, false);
            }

            ObjCRuntime.void_objc_msgSendSuper((new ObjCObject.Super(self, ObjCObject.NS_OBJECT_CLASS)).getHandle(), sel);
        }

        static {
            int ptrSize = VoidPtr.sizeOf();
            int alignment = ptrSize == 4?2:3;
            long cls = ObjCRuntime.objc_allocateClassPair(ObjCObject.NS_OBJECT_CLASS, VM.getStringUTFChars("RoboVMReleaseListener"), (long)ptrSize);
            if(cls == 0L) {
                throw new Error("Failed to create the RoboVMReleaseListener Objective-C class: objc_allocateClassPair(...) failed");
            } else if(!ObjCRuntime.class_addIvar(cls, VM.getStringUTFChars("value"), ptrSize, (byte)alignment, VM.getStringUTFChars("?"))) {
                throw new Error("Failed to create the RoboVMAssocObjWrapper Objective-C class: class_addIvar(...) failed");
            } else {
                Method releaseMethod = null;

                try {
                    releaseMethod = ObjCObject.AssociatedObjectHelper.class.getDeclaredMethod("release", new Class[]{Long.TYPE, Long.TYPE});
                } catch (Throwable var9) {
                    throw new Error(var9);
                }

                long superReleaseMethod = ObjCRuntime.class_getInstanceMethod(ObjCObject.NS_OBJECT_CLASS, release.getHandle());
                long releaseType = ObjCRuntime.method_getTypeEncoding(superReleaseMethod);
                if(!ObjCRuntime.class_addMethod(cls, release.getHandle(), VM.getCallbackMethodImpl(releaseMethod), releaseType)) {
                    throw new Error("Failed to create the RoboVMReleaseListener Objective-C class: class_addMethod(...) failed");
                } else {
                    ObjCRuntime.objc_registerClassPair(cls);
                    RELEASE_LISTENER_CLASS = cls;
                    OWNER_IVAR_OFFSET = ObjCRuntime.ivar_getOffset(ObjCRuntime.class_getInstanceVariable(cls, VM.getStringUTFChars("value")));
                }
            }
        }
    }

    static class ObjectOwnershipHelper {
        private static final LongMap<Object> CUSTOM_OBJECTS = new LongMap();
        private static final long retainCount = Selector.register("retainCount").getHandle();
        private static final long retain = Selector.register("retain").getHandle();
        private static final long originalRetain = Selector.register("original_retain").getHandle();
        private static final long release = Selector.register("release").getHandle();
        private static final long originalRelease = Selector.register("original_release").getHandle();
        private static final Method retainMethod;
        private static final Method releaseMethod;
        private static final LongMap<Long> customClassToNativeSuper = new LongMap();
        private static final Long ZERO_LONG = Long.valueOf(0L);

        ObjectOwnershipHelper() {
        }

        public static void registerClass(long cls) {
            registerCallbackMethod(cls, retain, originalRetain, retainMethod);
            registerCallbackMethod(cls, release, originalRelease, releaseMethod);
        }

        private static void registerCallbackMethod(long cls, long selector, long newSelector, Method method) {
            long superMethod = ObjCRuntime.class_getInstanceMethod(cls, selector);
            long typeEncoding = ObjCRuntime.method_getTypeEncoding(superMethod);
            if(!ObjCRuntime.class_addMethod(cls, selector, VM.getCallbackMethodImpl(method), typeEncoding)) {
                throw new Error("Failed to register callback method on the ObjectOwnershipHelper: class_addMethod(...) failed");
            } else {
                long superClass = ObjCRuntime.class_getSuperclass(cls);

                long nativeSuper;
                for(nativeSuper = 0L; superClass != 0L; superClass = ObjCRuntime.class_getSuperclass(superClass)) {
                    ObjCClass objCClass = ObjCClass.toObjCClass(superClass);
                    if(!objCClass.isCustom()) {
                        nativeSuper = superClass;
                        break;
                    }
                }

                if(nativeSuper == 0L) {
                    throw new Error("Couldn\'t find native super class for " + VM.newStringUTF(ObjCRuntime.class_getName(cls)));
                } else {
                    LongMap objCClass1 = customClassToNativeSuper;
                    synchronized(customClassToNativeSuper) {
                        customClassToNativeSuper.put(cls, Long.valueOf(nativeSuper));
                    }
                }
            }
        }

        @Callback
        @Pointer
        private static long retain(@Pointer long self, @Pointer long sel) {
            int count = ObjCRuntime.int_objc_msgSend(self, retainCount);
            if(count <= 1) {
                LongMap cls = CUSTOM_OBJECTS;
                synchronized(CUSTOM_OBJECTS) {
                    ObjCClass cls1 = ObjCClass.toObjCClass(ObjCRuntime.object_getClass(self));
                    ObjCObject sup = ObjCObject.toObjCObject(cls1.getType(), self, 0);
                    CUSTOM_OBJECTS.put(self, sup);
                }
            }

            long cls2 = ObjCRuntime.object_getClass(self);
            if(ObjCObject.logRetainRelease) {
                //ObjCObject.access$100(cls2, self, count, true);
            }

            ObjCObject.Super sup1 = new ObjCObject.Super(self, getNativeSuper(cls2));
            return ObjCRuntime.ptr_objc_msgSendSuper(sup1.getHandle(), sel);
        }

        @Callback
        private static void release(@Pointer long self, @Pointer long sel) {
            int count = ObjCRuntime.int_objc_msgSend(self, retainCount);
            if(count <= 2) {
                LongMap cls = CUSTOM_OBJECTS;
                synchronized(CUSTOM_OBJECTS) {
                    CUSTOM_OBJECTS.remove(self);
                }
            }

            long cls1 = ObjCRuntime.object_getClass(self);
            if(ObjCObject.logRetainRelease) {
                //ObjCObject.access$100(cls1, self, count, false);
            }

            ObjCObject.Super sup = new ObjCObject.Super(self, getNativeSuper(cls1));
            ObjCRuntime.void_objc_msgSendSuper(sup.getHandle(), sel);
        }

        public static boolean isObjectRetained(ObjCObject object) {
            LongMap var1 = CUSTOM_OBJECTS;
            synchronized(CUSTOM_OBJECTS) {
                return CUSTOM_OBJECTS.containsKey(object.getHandle());
            }
        }

        private static long getNativeSuper(long cls) {
            long c = cls;
            LongMap classHierarchy = customClassToNativeSuper;
            synchronized(customClassToNativeSuper) {
                while(c != 0L) {
                    long nativeSuper = ((Long)customClassToNativeSuper.get(c, ZERO_LONG)).longValue();
                    if(nativeSuper != 0L) {
                        return nativeSuper;
                    }

                    c = ObjCRuntime.class_getSuperclass(c);
                }
            }

            ArrayList classHierarchy1 = new ArrayList();

            for(c = cls; c != 0L; c = ObjCRuntime.class_getSuperclass(c)) {
                classHierarchy1.add(VM.newStringUTF(ObjCRuntime.class_getName(c)));
            }

            throw new Error("Failed to find a custom class to native super class mapping for class hierarchy " + classHierarchy1);
        }

        static {
            try {
                retainMethod = ObjCObject.ObjectOwnershipHelper.class.getDeclaredMethod("retain", new Class[]{Long.TYPE, Long.TYPE});
                releaseMethod = ObjCObject.ObjectOwnershipHelper.class.getDeclaredMethod("release", new Class[]{Long.TYPE, Long.TYPE});
            } catch (Throwable var1) {
                throw new Error(var1);
            }
        }
    }

    static class ObjCObjectRef extends WeakReference<ObjCObject> {
        public final long handle;

        public ObjCObjectRef(ObjCObject referent) {
            super(referent);
            this.handle = referent.getHandle();
        }
    }

    public static class Marshaler {
        public Marshaler() {
        }

        @MarshalsPointer
        public static ObjCObject toObject(Class<? extends ObjCObject> cls, long handle, long flags) {
            ObjCObject o = ObjCObject.toObjCObject(cls, handle, 0);
            return o;
        }

        @MarshalsPointer
        public static long toNative(ObjCObject o, long flags) {
            return o == null?0L:o.getHandle();
        }

        @MarshalsPointer
        public static ObjCProtocol protocolToObject(Class<?> cls, long handle, long flags) {
            Class proxyClass = (Class)ObjCClass.allObjCProxyClasses.get(cls.getName());
            if(proxyClass == null) {
                proxyClass = ObjCObject.class;
            }

            ObjCObject o = ObjCObject.toObjCObject(proxyClass, handle, 0);
            return (ObjCProtocol)o;
        }

        @MarshalsPointer
        public static long protocolToNative(ObjCProtocol o, long flags) {
            return o == null?0L:((ObjCObject)o).getHandle();
        }
    }

    public static class ObjCObjectPtr extends Ptr<ObjCObject, ObjCObject.ObjCObjectPtr> {
        public ObjCObjectPtr() {
        }
    }
}
