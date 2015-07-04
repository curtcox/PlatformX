package org.robovm.objc;

import java.util.HashMap;
import java.util.Map;

final class LongPointer {

    private static final Map<Long,Object> values = new HashMap();

    public static long to(Object o) {
        long pointer = pointer(o);
        values.put(pointer,o);
        return pointer;
    }

    public static Object value(long pointer) {
        return values.get(pointer);
    }

    private static long pointer(Object o) {
        return System.identityHashCode(o);
    }
}
