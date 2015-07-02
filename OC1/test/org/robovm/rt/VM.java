package org.robovm.rt;

import libcore.util.EmptyArray;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;

public final class VM {
    private static HashMap<String, byte[]> runtimeData;

    public VM() {}

    public static String bootClassPath() { throw never(); }

    public static String classPath() { throw never();}

    public static  String executablePath() { throw never(); }

    public static  String basePath() { throw never(); }

    public static  String[] staticLibs() { throw never(); }

    public static String vmVersion() {
        return "1.2.0";
    }

    public static final  Class<?>[] getStackClasses(int var0, int var1) {
        return new Class[] {};
    }

    public static final Class<?>[] listClasses(Class<?> assignableToClass, ClassLoader classLoader) {
        Class[] result = listClasses0(assignableToClass, classLoader);
        return result == null?EmptyArray.CLASS:result;
    }

    public static final byte[] getRuntimeData(String id) {
        try {
            return getRuntimeData0(id);
        } catch (UnsupportedEncodingException var2) {
            throw new Error(var2);
        }
    }

    private static final byte[] getRuntimeData0(String id) throws UnsupportedEncodingException {
        if(runtimeData == null) {
            HashMap map = new HashMap();
            byte[] allData = getRuntimeData0();
            if(allData != null) {
                ByteBuffer bb = ByteBuffer.wrap(allData).order(ByteOrder.nativeOrder());
                int pairCount = bb.getInt();

                for(int i = 0; i < pairCount; ++i) {
                    int keySize = bb.getInt();
                    String key = new String(allData, bb.position(), keySize, "UTF8");
                    bb.position(bb.position() + keySize);
                    int dataSize = bb.getInt();
                    byte[] data = new byte[dataSize];
                    bb.get(data);
                    map.put(key, data);
                }
            }

            runtimeData = map;
        }

        return (byte[])runtimeData.get(id);
    }

    private static final  byte[] getRuntimeData0() { throw never(); }

    private static final  Class<?>[] listClasses0(Class<?> var0, ClassLoader var1) { throw never(); }

    public static final  void generateHeapDump() {}

    public static final  long allocateMemory(int var0) { throw never(); }

    public static final  long allocateMemoryUncollectable(int var0) { throw never(); }

    public static final  long allocateMemoryAtomic(int var0) { throw never(); }

    public static final  long freeMemoryUncollectable(long var0) { throw never(); }

    public static  void registerDisappearingLink(long var0, Object var2) {}

    public static  void unregisterDisappearingLink(long var0) {}

    public static final  long malloc(int var0) { throw never(); }

    public static final  void free(long var0) {}

    public static final  <T> T allocateObject(Class<T> var0) { throw never(); }

    public static final  ByteBuffer newDirectByteBuffer(long var0, long var2) { throw never(); }

    public static final  void memcpy(long var0, long var2, long var4) {}

    public static final  void memmove8(long var0, long var2, long var4) {}

    public static final  void memmove16(long var0, long var2, long var4) {}

    public static final  void memmove32(long var0, long var2, long var4) {}

    public static final  void memmove64(long var0, long var2, long var4) {}

    public static final  void memset(long var0, byte var2, long var3) { }

    public static final  long getCallbackMethodImpl(Method var0) { throw never(); }

    public static final  void bindBridgeMethod(Method var0, long var1) { }

    public static final  boolean isBridgeMethodBound(Method var0) { throw never(); }

    public static final  long getObjectAddress(Object var0) { throw never(); }

    public static final  Object castAddressToObject(long var0) { throw never(); }

    public static final  long getFieldAddress(Field var0) { throw never(); }

    public static final  int getInstanceFieldOffset(long var0) { throw never(); }

    public static final  long getClassFieldAddress(long var0) { throw never(); }

    public static final  Object getObject(long var0) { throw never(); }

    public static final  double getDouble(long var0) { throw never(); }

    public static final  float getFloat(long var0) { throw never(); }

    public static final  long getLong(long var0) { throw never(); }

    public static final  int getInt(long var0) { throw never(); }

    public static final  char getChar(long var0) { throw never(); }

    public static final  short getShort(long var0){ throw never(); }

    public static final  byte getByte(long var0) { throw never(); }

    public static final  boolean getBoolean(long var0) { throw never(); }

    public static final  void setObject(long var0, Object var2) {}

    public static final  void setDouble(long var0, double var2) {}

    public static final  void setFloat(long var0, float var2) {}

    public static final  void setLong(long var0, long var2) {}

    public static final  void setInt(long var0, int var2) {}

    public static final  void setChar(long var0, char var2) {}

    public static final  void setShort(long var0, short var2) {}

    public static final  void setByte(long var0, byte var2) {}

    public static final  void setBoolean(long var0, boolean var2){}

    public static final  long getPointer(long var0) {throw never();}

    public static final  void setPointer(long var0, long var2){}

    public static final  long getStringUTFChars(String var0){ throw never();}

    public static final  String newStringUTF(long var0){ throw never(); }

    public static final  String newStringNoCopy(char[] var0, int var1, int var2){ throw never(); }

    public static final  long getArrayValuesAddress(Object var0){ throw never(); }

    public static final  boolean[] newBooleanArray(long var0, int var2){ throw never(); }

    public static final  byte[] newByteArray(long var0, int var2){ throw never(); }

    public static final  char[] newCharArray(long var0, int var2){ throw never(); }

    public static final  short[] newShortArray(long var0, int var2){ throw never(); }

    public static final  int[] newIntArray(long var0, int var2) { throw never(); }

    public static final  long[] newLongArray(long var0, int var2) { throw never(); }

    public static final  float[] newFloatArray(long var0, int var2) { throw never(); }

    public static final  double[] newDoubleArray(long var0, int var2) { throw never(); }

    private static RuntimeException never() {
        throw new RuntimeException();
    }
}
