package se.util;

import common.util.StringMap;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaggedValueStringMapTest {

    @Test
    public void can_create() {
        assertNotNull(new TaggedValueStringMap());
    }

    @Test
    public void is_StringMap() {
        assertTrue(new TaggedValueStringMap() instanceof StringMap);
    }

}