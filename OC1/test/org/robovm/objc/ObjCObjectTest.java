package org.robovm.objc;

import org.junit.Test;
import static org.junit.Assert.*;

public class ObjCObjectTest {

    @Test
    public void can_create_by_overriding_alloc_to_supply_a_handle() {
        assertNotNull(new ObjCObject(){
            protected long alloc() {
                return 42;
            }
        });
    }
}