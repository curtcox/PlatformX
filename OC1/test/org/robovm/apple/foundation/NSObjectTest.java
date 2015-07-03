package org.robovm.apple.foundation;

import org.junit.Test;
import static org.junit.Assert.*;

public class NSObjectTest {

    @Test
    public void can_create() {
        assertNotNull(new NSObject());
    }
}