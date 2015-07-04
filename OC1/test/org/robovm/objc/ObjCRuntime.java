package org.robovm.objc;

import org.robovm.rt.VM;
import org.robovm.rt.bro.Bro;
import org.robovm.rt.bro.Runtime;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.BytePtr;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Library("objc")
public class ObjCRuntime {
    private static final Map<Class<?>, Integer> structSizes = new HashMap();

    public ObjCRuntime() {
    }

    public static void bind() {
//        Class caller = VM.getStackClasses(0, 1)[0];
//        bind(caller);
    }

    public static void bind(Class<?> c) {
        Method[] var1 = c.getDeclaredMethods();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Method method = var1[var3];
            Bridge bridge = (Bridge)method.getAnnotation(Bridge.class);
            if(bridge != null && (bridge.symbol() == null || "".equals(bridge.symbol()))) {
                Class[] paramTypes = method.getParameterTypes();
                if(paramTypes.length >= 2 && paramTypes[1] == Selector.class) {
                    String symbol = null;
                    if(paramTypes[0] == ObjCSuper.class) {
                        symbol = "objc_msgSendSuper";
                    } else if(ObjCObject.class.isAssignableFrom(paramTypes[0])) {
                        symbol = "objc_msgSend";
                    } else if(paramTypes[0] == Long.TYPE) {
                        Annotation[] f = method.getParameterAnnotations()[0];
                        Annotation[] var9 = f;
                        int var10 = f.length;

                        for(int var11 = 0; var11 < var10; ++var11) {
                            Annotation a = var9[var11];
                            if(a.annotationType() == Pointer.class) {
                                symbol = "objc_msgSend";
                                break;
                            }
                        }
                    }

                    if(symbol != null) {
                        if(isStret(method)) {
                            symbol = symbol + "_stret";
                        }

                        long var13 = Runtime.resolveBridge("objc", symbol, method);
                        VM.bindBridgeMethod(method, var13);
                    }
                }
            }
        }

        //Bro.bind(c);
    }

    static boolean isStret(Method method) {
        Class returnType = method.getReturnType();
        if(returnType.getSuperclass() == Struct.class && (method.getAnnotation(ByVal.class) != null || returnType.getAnnotation(ByVal.class) != null)) {
            int structSize = getStructSize(returnType);
            if(Bro.IS_X86 && Bro.IS_32BIT) {
                if(structSize > 2 && structSize != 4 && structSize != 8) {
                    return true;
                }
            } else if(Bro.IS_X86 && Bro.IS_64BIT) {
                if(structSize > 16) {
                    return true;
                }
            } else {
                if(!Bro.IS_ARM || !Bro.IS_32BIT) {
                    if(Bro.IS_ARM && Bro.IS_64BIT) {
                        return false;
                    }

                    throw new Error("Unsupported architecture");
                }

                if(structSize > 4) {
                    return true;
                }
            }
        }

        return false;
    }

    static synchronized int getStructSize(Class<?> cls) {
        Integer size = (Integer)structSizes.get(cls);
        if(size == null) {
            try {
                Method e = cls.getMethod("sizeOf", new Class[0]);
                size = (Integer)e.invoke((Object)null, new Object[0]);
            } catch (Exception var3) {
                throw new Error(var3);
            }

            structSizes.put(cls, size);
        }

        return size.intValue();
    }

    @Bridge
    @Pointer
    public static long objc_getClass(@Pointer long var0) { throw never(); }

    @Bridge
    public static BytePtr sel_getName(Selector var0) { throw never(); }

    @Bridge
    public static Selector sel_registerName(BytePtr var0)  { throw never(); }

    @Bridge
    public static void objc_setAssociatedObject(@Pointer long var0, @Pointer long var2, @Pointer long var4, int var6)  { throw never(); }

    @Bridge
    @Pointer
    public static long objc_getAssociatedObject(@Pointer long var0, @Pointer long var2)  { throw never(); }

    @Bridge
    @Pointer
    public static long objc_allocateClassPair(@Pointer long var0, @Pointer long var2, @Pointer long var4)  { throw never(); }

    @Bridge
    public static void objc_registerClassPair(@Pointer long var0)  { throw never(); }

    @Bridge
    @Pointer
    public static long object_getClass(@Pointer long var0)  { return var0; }

    @Bridge
    @Pointer
    public static long object_getClassName(@Pointer long var0)  { throw never(); }
    @Bridge
    @Pointer
    public static long class_getSuperclass(@Pointer long var0)  { throw never(); }

    @Bridge
    @Pointer
    public static long class_getName(@Pointer long classHandle)  {
        Class c = (Class) LongPointer.value(classHandle);
        String name = c.getName();
        return LongPointer.to(name);
    }

    @Bridge
    @Pointer
    public static long class_getInstanceMethod(@Pointer long var0, @Pointer long var2)  { throw never(); }

    @Bridge
    @Pointer
    public static long class_getClassMethod(@Pointer long var0, @Pointer long var2)  { throw never(); }

    @Bridge
    public static boolean class_addMethod(@Pointer long var0, @Pointer long var2, @Pointer long var4, @Pointer long var6)  { throw never(); }

    @Bridge
    @Pointer
    public static long class_replaceMethod(@Pointer long var0, @Pointer long var2, @Pointer long var4, @Pointer long var6)  { throw never(); }

    @Bridge
    public static boolean class_addIvar(@Pointer long var0, @Pointer long var2, int var4, byte var5, @Pointer long var6)  { throw never(); }

    @Bridge
    @Pointer
    public static long class_getInstanceVariable(@Pointer long var0, @Pointer long var2)  { throw never(); }
    @Bridge
    @Pointer
    public static long class_getIvarLayout(@Pointer long var0)  { throw never(); }
    @Bridge
    public static int ivar_getOffset(@Pointer long var0)  { throw never(); }

    @Bridge
    @Pointer
    public static long method_getTypeEncoding(@Pointer long var0)  { throw never(); }

    @Bridge
    @Pointer
    public static long method_getImplementation(@Pointer long var0)  { throw never(); }

    @Bridge
    @Pointer
    public static long method_setImplementation(@Pointer long var0, @Pointer long var2)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSend"
    )
    @Pointer
    public static long ptr_objc_msgSend(@Pointer long var0, @Pointer long var2)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSendSuper"
    )
    @Pointer
    public static long ptr_objc_msgSendSuper(@Pointer long var0, @Pointer long var2)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSend"
    )
    public static void void_objc_msgSend(@Pointer long var0, @Pointer long var2)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSendSuper"
    )
    public static void void_objc_msgSendSuper(@Pointer long var0, @Pointer long var2)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSend"
    )
    public static boolean boolean_objc_msgSend(@Pointer long var0, @Pointer long var2)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSend"
    )
    public static void void_objc_msgSend_ptr(@Pointer long var0, @Pointer long var2, @Pointer long var4)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSend"
    )
    public static void void_objc_msgSend_boolean(@Pointer long var0, @Pointer long var2, boolean var4)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSend"
    )
    public static int int_objc_msgSend(@Pointer long var0, @Pointer long var2)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSend"
    )
    @Pointer
    public static long ptr_objc_msgSend_ptr(@Pointer long var0, @Pointer long var2, @Pointer long var4)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSend"
    )
    @Pointer
    public static long ptr_objc_msgSend_int(@Pointer long var0, @Pointer long var2, int var4)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSend"
    )
    @Pointer
    public static long ptr_objc_msgSend_long(@Pointer long var0, @Pointer long var2, long var4)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSend"
    )
    public static char char_objc_msgSend_int(@Pointer long var0, @Pointer long var2, int var4)  { throw never(); }

    @Bridge(
            symbol = "objc_msgSend_stret",
            optional = true
    )
    public static void objc_msgSend_stret(@StructRet @Pointer long var0, @Pointer long var2, @Pointer long var4)  { throw never(); }
//    static {
//        Bro.bind();
//    }

    private static RuntimeException never() {
        throw new RuntimeException();
    }
}
