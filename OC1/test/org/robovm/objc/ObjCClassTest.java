package org.robovm.objc;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class ObjCClassTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
    }

    @Test
    public void can_create_from_object_handle() {
        long handle = 0;
        assertNotNull(ObjCClass.getFromObject(handle));
    }

    @Test
    public void getFromObject_returns_ObjCClass() {
        long handle = 42;
        boolean customClass = true;
        ObjCObject id = new ObjCObject(handle,customClass) {};
        ObjCClass actual = ObjCClass.getFromObject(id);

        assertNotNull(actual);
    }
}