package org.robovm.objc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ObjCRuntimeTest {

    @Test
    public void class_getName_returns_pointer_to_class_name() {
        Class c = Object.class;
        long pointer = ObjCRuntime.class_getName(LongPointer.to(c));
        Object value = LongPointer.value(pointer);
        assertEquals(c.getName(), value);
    }
}