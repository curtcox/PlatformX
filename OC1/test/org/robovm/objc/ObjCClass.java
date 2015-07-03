//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.robovm.objc;

import org.robovm.objc.annotation.BindSelector;
import org.robovm.objc.annotation.CustomClass;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.annotation.TypeEncoding;
import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.MarshalsPointer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@Library("objc")
public final class ObjCClass extends ObjCObject {
    private static final Map<Class<? extends ObjCObject>, ObjCClass> typeToClass = new HashMap();
    private static final Map<String, ObjCClass> nameToClass = new HashMap();
    private static final Map<String, Class<? extends ObjCObject>> allNativeClasses = new HashMap();
    private static final Map<String, Class<? extends ObjCObject>> allCustomClasses = new HashMap();
    static final Map<String, Class<? extends ObjCObject>> allObjCProxyClasses = new HashMap();
    private static final int ACC_SYNTHETIC = 4096;
    private static final String CUSTOM_CLASS_NAME_PREFIX = "j_";
    private final Class<? extends ObjCObject> type;
    private final String name;
    private final boolean custom;

    static boolean isObjCProxy(Class<?> cls) {
        return (cls.getModifiers() & 4096) > 0 && cls.getName().endsWith("$ObjCProxy");
    }

    private ObjCClass(long handle, Class<? extends ObjCObject> type, String name, boolean custom) {
        super(handle, false);
        this.type = type;
        this.name = name;
        this.custom = custom;
    }

    public Class<? extends ObjCObject> getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCustom() {
        return this.custom;
    }

    public String toString() {
        return this.type.getName();
    }

    public static ObjCClass getByName(String objcClassName) {
        Object var1 = objcBridgeLock;
        synchronized(objcBridgeLock) {
            ObjCClass c = (ObjCClass)nameToClass.get(objcClassName);
            if(c == null) {
                c = getByNameNotLoaded(objcClassName);
                if(c == null) {
                    throw new ObjCClassNotFoundException("Could not find Java class corresponding to Objective-C class: " + objcClassName);
                }
            }

            return c;
        }
    }

    private static ObjCClass getByNameNotLoaded(String objcClassName) {
        Class cls = (Class)allNativeClasses.get(objcClassName);
        if(cls != null) {
            return getByType(cls);
        } else {
            cls = (Class)allCustomClasses.get(objcClassName);
            return cls != null?getByType(cls):null;
        }
    }

    public static ObjCClass getFromObject(ObjCObject id) {
        long handle = id.getHandle();
        ObjCClass c = null;
        if(handle != 0L) {
            long classPtr = ObjCRuntime.object_getClass(handle);
            c = (ObjCClass)ObjCObject.getPeerObject(classPtr);
        }

        return c != null?c:getByType(id.getClass());
    }

    public static ObjCClass getFromObject(long handle) {
        long classPtr = ObjCRuntime.object_getClass(handle);
        return toObjCClass(classPtr);
    }

    public static ObjCClass getByType(Class<? extends ObjCObject> type) {
        if(type == null) {
            throw new NullPointerException("type");
        } else {
            Object var1 = objcBridgeLock;
            synchronized(objcBridgeLock) {
                ObjCClass c = (ObjCClass)typeToClass.get(type);
                if(c == null) {
                    NativeClass nativeClassAnno = (NativeClass)type.getAnnotation(NativeClass.class);
                    String name = null;
                    if(nativeClassAnno != null) {
                        name = nativeClassAnno.value();
                        name = "".equals(name)?type.getSimpleName():name;
                    } else {
                        name = getCustomClassName(type);
                        c = register(type, name);
                    }

                    if(c == null) {
                        long classPtr = ObjCRuntime.objc_getClass(VM.getStringUTFChars(name));
                        if(classPtr == 0L) {
                            throw new ObjCClassNotFoundException(name);
                        }

                        c = new ObjCClass(classPtr, type, name, false);
                    }

                    typeToClass.put(type, c);
                    nameToClass.put(name, c);
                }

                return c;
            }
        }
    }

    public static ObjCClass toObjCClass(long handle) {
        long classPtr = handle;
        ObjCClass c = (ObjCClass)ObjCObject.getPeerObject(handle);
        if(c == null) {
            c = getByNameNotLoaded(VM.newStringUTF(ObjCRuntime.class_getName(handle)));
        }

        while(c == null && classPtr != 0L) {
            classPtr = ObjCRuntime.class_getSuperclass(classPtr);
            c = (ObjCClass)ObjCObject.getPeerObject(classPtr);
            if(c == null) {
                c = getByNameNotLoaded(VM.newStringUTF(ObjCRuntime.class_getName(classPtr)));
            }
        }

        if(c == null) {
            String name = VM.newStringUTF(ObjCRuntime.class_getName(handle));
            throw new ObjCClassNotFoundException("Could not find Java class corresponding to Objective-C class: " + name);
        } else {
            return c;
        }
    }

    public static ObjCClass registerCustomClass(Class<? extends ObjCObject> type) {
        if(type.getAnnotation(NativeClass.class) != null) {
            throw new IllegalArgumentException("@NativeClass annotated class " + type.getName() + " can not be registered as a custom class");
        } else {
            Object var1 = objcBridgeLock;
            synchronized(objcBridgeLock) {
                ObjCClass c = (ObjCClass)typeToClass.get(type);
                if(c == null) {
                    String name = getCustomClassName(type);
                    c = register(type, name);
                    typeToClass.put(type, c);
                    nameToClass.put(name, c);
                }

                return c;
            }
        }
    }

    private static String getCustomClassName(Class<? extends ObjCObject> type) {
        CustomClass customClassAnno = (CustomClass)type.getAnnotation(CustomClass.class);
        String name = type.getName();
        if(customClassAnno != null && customClassAnno.value().length() > 0) {
            name = customClassAnno.value();
        } else {
            name = "j_" + name;
        }

        name = name.replace('.', '_');
        return name;
    }

    private static ObjCClass register(Class<? extends ObjCObject> type, String name) {
        ObjCClass superclass = getByType((Class<? extends ObjCObject>) type.getSuperclass());
        long handle = ObjCRuntime.objc_allocateClassPair(superclass.getHandle(), VM.getStringUTFChars(name), 0L);
        if(handle == 0L) {
            throw new ObjCClassNotFoundException("Failed to create custom Objective-C class for Java class: " + type);
        } else {
            Iterator var5 = getCallbacks(type).entrySet().iterator();

            while(var5.hasNext()) {
                Entry entry = (Entry)var5.next();
                String selName = (String)entry.getKey();
                Method method = (Method)entry.getValue();
                boolean isClassMethod = method.getParameterTypes()[0] == ObjCClass.class;
                if(!isClassMethod || method.getDeclaringClass() == type) {
                    Selector selector = Selector.register(selName);
                    String encoding = null;
                    TypeEncoding typeEncoding = (TypeEncoding)method.getAnnotation(TypeEncoding.class);
                    long impl;
                    long ownerHandle;
                    if(typeEncoding != null) {
                        encoding = typeEncoding.value();
                    } else {
                        impl = isClassMethod?ObjCRuntime.class_getClassMethod(superclass.getHandle(), selector.getHandle()):ObjCRuntime.class_getInstanceMethod(superclass.getHandle(), selector.getHandle());
                        if(impl != 0L) {
                            ownerHandle = ObjCRuntime.method_getTypeEncoding(impl);
                            if(ownerHandle != 0L) {
                                encoding = VM.newStringUTF(ownerHandle);
                            }
                        }
                    }

                    impl = VM.getCallbackMethodImpl(method);
                    ownerHandle = isClassMethod?ObjCRuntime.object_getClass(handle):handle;
                    if(!ObjCRuntime.class_addMethod(ownerHandle, selector.getHandle(), impl, encoding != null?VM.getStringUTFChars(encoding):0L)) {
                        throw new ObjCClassNotFoundException("Failed to add method " + selName + " to custom Objective-C class for Java class: " + type);
                    }
                }
            }

            ObjectOwnershipHelper.registerClass(handle);
            ObjCRuntime.objc_registerClassPair(handle);
            return new ObjCClass(handle, type, name, true);
        }
    }

    private static Map<String, Method> getCallbacks(Class<?> type) {
        HashMap callbacks = new HashMap();
        findCallbacks(type, callbacks);
        return callbacks;
    }

    private static void findCallbacks(Class<?> type, Map<String, Method> result) {
        Method[] var2 = type.getDeclaredMethods();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Method m = var2[var4];
            if(m.getAnnotation(Callback.class) != null) {
                BindSelector bindSelector = (BindSelector)m.getAnnotation(BindSelector.class);
                if(bindSelector != null && !result.containsKey(bindSelector.value())) {
                    result.put(bindSelector.value(), m);
                }
            }
        }

    }

    static {
//        ObjCRuntime.bind();
//        Class[] classes = (Class[])VM.listClasses(ObjCObject.class, ClassLoader.getSystemClassLoader());
//        Class[] var1 = classes;
//        int var2 = classes.length;
//
//        for(int var3 = 0; var3 < var2; ++var3) {
//            Class cls = var1[var3];
//            NativeClass nativeClassAnno = (NativeClass)cls.getAnnotation(NativeClass.class);
//            String name;
//            String protocolName;
//            if(nativeClassAnno != null) {
//                name = nativeClassAnno.value();
//                if(name.length() == 0) {
//                    name = cls.getSimpleName();
//                }
//
//                allNativeClasses.put(name, cls);
//            } else {
//                CustomClass var9 = (CustomClass)cls.getAnnotation(CustomClass.class);
//                protocolName = cls.getName();
//                if(var9 != null) {
//                    String value = var9.value();
//                    if(value.length() > 0) {
//                        protocolName = value;
//                    }
//                } else if(protocolName.indexOf(46) == -1) {
//                    protocolName = "." + protocolName;
//                }
//
//                allCustomClasses.put(protocolName, cls);
//            }
//
//            if(isObjCProxy(cls)) {
//                name = cls.getName();
//                protocolName = name.substring(0, name.length() - 10);
//                allObjCProxyClasses.put(protocolName, cls);
//            }
//        }

    }

    public static class Marshaler {
        public Marshaler() {
        }

        @MarshalsPointer
        public static Class<? extends ObjCObject> toObject(Class<ObjCClass> cls, long handle, long flags) {
            ObjCClass o = ObjCClass.toObjCClass(handle);
            return o == null?null:o.getType();
        }

        @MarshalsPointer
        public static long toNative(Class<? extends ObjCObject> o, long flags) {
            if(o == null) {
                return 0L;
            } else {
                ObjCClass c = ObjCClass.getByType(o);
                return c.getHandle();
            }
        }
    }
}
