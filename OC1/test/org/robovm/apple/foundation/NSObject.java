package org.robovm.apple.foundation;

import org.robovm.apple.foundation.NSArray.AsListMarshaler;
import org.robovm.apple.foundation.NSString.AsStringMarshaler;
import org.robovm.objc.*;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.annotation.Property;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.Ptr;
import org.robovm.rt.bro.ptr.VoidPtr;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Library("Foundation")
@NativeClass
@Marshalers({    @org.robovm.rt.bro.annotation.Marshaler(AsStringMarshaler.class),     @org.robovm.rt.bro.annotation.Marshaler(AsListMarshaler.class),     @org.robovm.rt.bro.annotation.Marshaler(NSObject.Marshaler.class)})
public class NSObject extends ObjCObject implements NSObjectProtocol {
    public static final int FLAG_NO_RETAIN = 1;
    private NSKeyValueCoder keyValueCoder;
    private static final Selector alloc;
    private static final Selector retain;
    private static final Selector release;
    private static final Selector autorelease;
    private Set<NSObject.KeyValueObserverWrapper> keyValueObservers;

    public NSObject() {
        this.initObject(this.init());
    }

    protected NSObject(long handle) {
        super(handle);
    }

    protected NSObject(NSObject.SkipInit skipInit) {
    }

    @Property(
            selector = "classForCoder"
    )
    public Class<? extends NSObject> getClassForCoder() {
        throw never();
    }

    @Property(
            selector = "autoContentAccessingProxy"
    )
    public NSObject getAutoContentAccessingProxy() {
        throw never();
    }

    @Property(
            selector = "observationInfo"
    )
    public VoidPtr getObservationInfo() {
        throw never();
    }

    @Property(
            selector = "setObservationInfo:"
    )
    public void setObservationInfo(VoidPtr var1) {
        throw never();
    }

    @Property(
            selector = "classForKeyedArchiver"
    )
    public Class<? extends NSObject> getClassForKeyedArchiver() {
        throw never();
    }

    protected void afterMarshaled(int flags) {
        super.afterMarshaled(flags);
        if((flags & 1) == 0) {
            retain(this.getHandle());
        }

    }

    protected long alloc() {
        return alloc(this.getClass());
    }

    static long alloc(Class c) {
        return c.hashCode();
//        long h = ObjCRuntime.ptr_objc_msgSend(c.getHandle(), alloc.getHandle());
//        if(h == 0L) {
//            throw new OutOfMemoryError();
//        } else {
//            return h;
//        }
    }

    public boolean equals(Object obj) {
        return !(obj instanceof NSObject)?false:this.isEqual((NSObject)obj);
    }

    public int hashCode() {
        long h = this.hash();
        return (int)(h ^ h >>> 32);
    }

    protected void doDispose() {
        super.doDispose();
        long handle = this.getHandle();
        if(handle != 0L) {
            release(handle);
        }

    }

    public String toString() {
        return this.description();
    }

    protected static void retain(long handle) {
        ObjCRuntime.void_objc_msgSend(handle, retain.getHandle());
    }

    protected static void release(long handle) {
        ObjCRuntime.void_objc_msgSend(handle, release.getHandle());
    }

    protected static void autorelease(long handle) {
        ObjCRuntime.void_objc_msgSend(handle, autorelease.getHandle());
    }

    @Method(
            selector = "performSelector:"
    )
    public final NSObject performSelector(Selector var1) {
        throw never();
    }

    @Method(
            selector = "performSelector:withObject:"
    )
    public final NSObject performSelector(Selector var1, NSObject var2) {
        throw never();
    }

    @Method(
            selector = "performSelector:withObject:withObject:"
    )
    public final NSObject performSelector(Selector var1, NSObject var2, NSObject var3) {
        throw never();
    }

    @Method(
            selector = "isEqual:"
    )
    public boolean isEqual(NSObject var1) {
        throw never();
    }

    @Method(
            selector = "hash"
    )
    @MachineSizedUInt
    public long hash() {
        throw never();
    }

    @Method(
            selector = "isKindOfClass:"
    )
    public boolean isKindOfClass(ObjCClass var1) {
        throw never();
    }

    @Method(
            selector = "isMemberOfClass:"
    )
    public boolean isMemberOfClass(ObjCClass var1) {
        throw never();
    }

    @Method(
            selector = "conformsToProtocol:"
    )
    public boolean conformsToProtocol(ObjCProtocol var1) {
        throw never();
    }

    @Method(
            selector = "respondsToSelector:"
    )
    public boolean respondsToSelector(Selector var1) {
        throw never();
    }

    @Method(
            selector = "init"
    )
    @Pointer
    private long init() {
        throw never();
    }

    @Method(
            selector = "retain"
    )
    public final NSObject retain() {
        throw never();
    }

    @Method(
            selector = "release"
    )
    public final void release() {
        throw never();
    }

    @Method(
            selector = "autorelease"
    )
    public NSObject autorelease() {
        throw never();
    }

    @Method(
            selector = "retainCount"
    )
    @MachineSizedUInt
    public long retainCount() {
        throw never();
    }

    @Method(
            selector = "description"
    )
    public String description() {
        throw never();
    }

    @Method(
            selector = "performSelector:"
    )
    public final void performSelectorV(Selector var1) {
        throw never();
    }

    @Method(
            selector = "performSelector:withObject:"
    )
    public final void performSelectorV(Selector var1, NSObject var2) {
        throw never();
    }

    @Method(
            selector = "performSelector:withObject:withObject:"
    )
    public final void performSelectorV(Selector var1, NSObject var2, NSObject var3) {
        throw never();
    }

    public void addKeyValueObserver(String keyPath, NSObject.NSKeyValueObserver observer) {
        this.addKeyValueObserver(keyPath, observer, NSKeyValueObservingOptions.None);
    }

    public void addKeyValueObserver(String keyPath, NSObject.NSKeyValueObserver observer, NSKeyValueObservingOptions options) {
        if(keyPath == null) {
            throw new NullPointerException("keyPath");
        } else if(observer == null) {
            throw new NullPointerException("observer");
        } else {
            if(this.keyValueObservers == null) {
                this.keyValueObservers = new HashSet();
            }

            NSObject.KeyValueObserverWrapper wrapper = null;
            Set var5 = this.keyValueObservers;
            synchronized(this.keyValueObservers) {
                Iterator var6 = this.keyValueObservers.iterator();

                while(true) {
                    if(var6.hasNext()) {
                        NSObject.KeyValueObserverWrapper w = (NSObject.KeyValueObserverWrapper)var6.next();
                        if(w.observer != observer) {
                            continue;
                        }

                        wrapper = w;
                    }

                    if(wrapper != null) {
                        wrapper.keyPaths.add(keyPath);
                    } else {
                        //wrapper = new NSObject.KeyValueObserverWrapper(observer, keyPath, null);
                        this.addStrongRef(wrapper);
                        this.keyValueObservers.add(wrapper);
                    }
                    break;
                }
            }

            this.addObserver(wrapper, keyPath, options, (VoidPtr)null);
        }
    }

    public void removeKeyValueObservers(String keyPath) {
        if(keyPath == null) {
            throw new NullPointerException("keyPath");
        } else if(this.keyValueObservers != null) {
            Set var2 = this.keyValueObservers;
            synchronized(this.keyValueObservers) {
                Iterator i = this.keyValueObservers.iterator();

                while(i.hasNext()) {
                    NSObject.KeyValueObserverWrapper wrapper = (NSObject.KeyValueObserverWrapper)i.next();
                    if(wrapper.keyPaths.remove(keyPath)) {
                        this.removeObserver(wrapper, keyPath, (VoidPtr)null);
                        if(wrapper.keyPaths.size() == 0) {
                            i.remove();
                            this.removeStrongRef(wrapper);
                        }
                    }
                }

            }
        }
    }

    public void removeKeyValueObserver(String keyPath, NSObject.NSKeyValueObserver observer) {
        if(keyPath == null) {
            throw new NullPointerException("keyPath");
        } else if(observer == null) {
            throw new NullPointerException("observer");
        } else if(this.keyValueObservers != null) {
            NSObject.KeyValueObserverWrapper wrapper = null;
            Set var4 = this.keyValueObservers;
            synchronized(this.keyValueObservers) {
                Iterator var5 = this.keyValueObservers.iterator();

                while(var5.hasNext()) {
                    NSObject.KeyValueObserverWrapper w = (NSObject.KeyValueObserverWrapper)var5.next();
                    if(w.observer == observer) {
                        wrapper = w;
                        break;
                    }
                }

                if(wrapper != null && wrapper.keyPaths.remove(keyPath)) {
                    this.removeObserver(wrapper, keyPath, (VoidPtr)null);
                    if(wrapper.keyPaths.size() == 0) {
                        this.keyValueObservers.remove(wrapper);
                        this.removeStrongRef(wrapper);
                    }
                }

            }
        }
    }

    public NSKeyValueCoder getKeyValueCoder() {
        if(this.keyValueCoder == null) {
            this.keyValueCoder = new NSKeyValueCoder(this);
        }

        return this.keyValueCoder;
    }

    public void willChangeValues(String key, NSKeyValueChange changeKind, NSIndexSet indexes) {
        this.willChangeValues((NSKeyValueChange)changeKind, (NSIndexSet)indexes, (String)key);
    }

    public void didChangeValues(String key, NSKeyValueChange changeKind, NSIndexSet indexes) {
        this.didChangeValues((NSKeyValueChange)changeKind, (NSIndexSet)indexes, (String)key);
    }

    @Method(
            selector = "copy"
    )
    public NSObject copy() {
        throw never();
    }

    @Method(
            selector = "mutableCopy"
    )
    public NSObject mutableCopy() {
        throw never();
    }

    @Method(
            selector = "addObserver:forKeyPath:options:context:"
    )
    private void addObserver(NSObject var1, String var2, NSKeyValueObservingOptions var3, VoidPtr var4) {
        throw never();
    }

    @Method(
            selector = "removeObserver:forKeyPath:context:"
    )
    private void removeObserver(NSObject var1, String var2, VoidPtr var3) {
        throw never();
    }

    @Method(
            selector = "willChangeValueForKey:"
    )
    public void willChangeValue(String var1) {
        throw never();
    }

    @Method(
            selector = "didChangeValueForKey:"
    )
    public void didChangeValue(String var1) {
        throw never();
    }

    @Method(
            selector = "willChange:valuesAtIndexes:forKey:"
    )
    private void willChangeValues(NSKeyValueChange var1, NSIndexSet var2, String var3) {
        throw never();
    }

    @Method(
            selector = "didChange:valuesAtIndexes:forKey:"
    )
    private void didChangeValues(NSKeyValueChange var1, NSIndexSet var2, String var3) {
        throw never();
    }

    @Method(
            selector = "willChangeValueForKey:withSetMutation:usingObjects:"
    )
    public void willChangeValue(String var1, NSKeyValueSetMutationKind var2, NSSet<?> var3) {
        throw never();
    }

    @Method(
            selector = "didChangeValueForKey:withSetMutation:usingObjects:"
    )
    public void didChangeValue(String var1, NSKeyValueSetMutationKind var2, NSSet<?> var3) {
        throw never();
    }

    @Method(
            selector = "performSelector:withObject:afterDelay:inModes:"
    )
    public final void performSelector(Selector var1, NSObject var2, double var3, NSArray<?> var5) {
        throw never();
    }

    @Method(
            selector = "performSelector:withObject:afterDelay:"
    )
    public final void performSelector(Selector var1, NSObject var2, double var3) {
        throw never();
    }

    @Method(
            selector = "performSelectorOnMainThread:withObject:waitUntilDone:modes:"
    )
    public final void performSelectorOnMainThread(Selector var1, NSObject var2, boolean var3, NSArray<?> var4) {
        throw never();
    }

    @Method(
            selector = "performSelectorOnMainThread:withObject:waitUntilDone:"
    )
    public final void performSelectorOnMainThread(Selector var1, NSObject var2, boolean var3) {
        throw never();
    }

    @Method(
            selector = "performSelector:onThread:withObject:waitUntilDone:modes:"
    )
    public final void performSelector(Selector var1, NSThread var2, NSObject var3, boolean var4, NSArray<?> var5) {
        throw never();
    }

    @Method(
            selector = "performSelector:onThread:withObject:waitUntilDone:"
    )
    public final void performSelector(Selector var1, NSThread var2, NSObject var3, boolean var4) {
        throw never();
    }

    @Method(
            selector = "performSelectorInBackground:withObject:"
    )
    public final void performSelectorInBackground(Selector var1, NSObject var2) {
        throw never();
    }

    static {
        //ObjCRuntime.bind(NSObject.class);
        alloc = Selector.register("alloc");
        retain = Selector.register("retain");
        release = Selector.register("release");
        autorelease = Selector.register("autorelease");
    }

    private class KeyValueObserverWrapper extends NSObject {
        private NSObject.NSKeyValueObserver observer;
        private Set<String> keyPaths;

        private KeyValueObserverWrapper(NSObject.NSKeyValueObserver observer, String keyPath) {
            this.keyPaths = new HashSet();
            this.observer = observer;
            this.keyPaths.add(keyPath);
        }

        @Method(
                selector = "observeValueForKeyPath:ofObject:change:context:"
        )
        private void observeValueForKeyPath(String keyPath, NSObject object, NSKeyValueChangeInfo change, VoidPtr context) {
            this.observer.observeValue(keyPath, object, change);
        }
    }

    public interface NSKeyValueObserver {
        void observeValue(String var1, NSObject var2, NSKeyValueChangeInfo var3);
    }

    public static class NoRetainMarshaler {
        public NoRetainMarshaler() {
        }

        @MarshalsPointer
        public static NSObject toObject(Class<? extends NSObject> cls, long handle, long flags) {
            return NSObject.Marshaler.toObject(cls, handle, flags, false);
        }

        @MarshalsPointer
        public static long toNative(NSObject o, long flags) {
            return NSObject.Marshaler.toNative((NSObject)o, flags);
        }
    }

    public static class Marshaler {
        public Marshaler() {
        }

        @MarshalsPointer
        public static NSObject toObject(Class<? extends NSObject> cls, long handle, long flags) {
            return toObject(cls, handle, flags, true);
        }

        static NSObject toObject(Class<? extends NSObject> cls, long handle, long flags, boolean retain) {
            NSObject o = (NSObject)ObjCObject.toObjCObject(cls, handle, retain?0:1);
            return o;
        }

        @MarshalsPointer
        public static long toNative(NSObject o, long flags) {
            if(o == null) {
                return 0L;
            } else {
                long handle = o.getHandle();
                if((flags & 3L) > 0L) {
                    NSObject.retain(handle);
                    if((flags & 1L) > 0L) {
                        NSObject.autorelease(handle);
                    }
                }

                return handle;
            }
        }

        @MarshalsPointer
        public static long toNative(NSObjectProtocol o, long flags) {
            return toNative((NSObject)((NSObject)o), flags);
        }
    }

    public static class NSObjectPtr extends Ptr<NSObject, NSObject.NSObjectPtr> {
        public NSObjectPtr() {
        }
    }

    protected static class SkipInit {
        protected SkipInit() {
        }
    }

    private static RuntimeException never() {
        throw new RuntimeException();
    }
}
