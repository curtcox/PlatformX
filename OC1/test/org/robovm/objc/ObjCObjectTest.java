package org.robovm.objc;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class ObjCObjectTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
    }

    @Test
    public void can_create_by_overriding_alloc_to_supply_a_handle() {
        assertNotNull(new ObjCObject());
    }
}