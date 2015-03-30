package se.util;

import common.screen.ScreenTags;
import common.util.StringMap;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaggedValueStringMapTest {

    TaggedValueStringMap testObject = new TaggedValueStringMap();

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void is_StringMap() {
        assertTrue(testObject instanceof StringMap);
    }

    @Test
    public void newValue_returns_a_tagged_value() {
        assertTrue(testObject.newValue() instanceof TaggedValue);
    }

    @Test
    public void get_returns_contents_of_tagged_value_with_simple_tag() {
        String expected = "Io Europa Titan Callisto";
        String key = "moons";

        TaggedValue taggedValue = testObject.newValue();
        taggedValue.setTags(ScreenTags.of(key));
        taggedValue.setContents(expected);

        String actual = testObject.get(key);

        assertEquals(expected,actual);
    }
}