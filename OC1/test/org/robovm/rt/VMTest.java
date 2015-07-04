package org.robovm.rt;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import org.robovm.rt.bro.NativeObject;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class VMTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
    }

    @Test
    public void allocateObject_can_return_a_new_object() {
        assertTrue(VM.allocateObject(Object.class) instanceof Object);
    }

    static class A extends NativeObject {
        A(long h) { setHandle(h); }
    }

    @Test
    public void allocateObject_can_return_a_new_native_object() {
        assertTrue(VM.allocateObject(A.class) instanceof A);
    }

}