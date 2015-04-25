package se.util;

import common.screen.ScreenTags;
import common.util.StringMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SimpleTaggedValueStringMapTest {

    SimpleTaggedValueStringMap testObject = new SimpleTaggedValueStringMap();

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
    public void get_key_returns_contents_of_tagged_value_with_simple_tag_when_value_has_been_set() {
        String tags = random("tags");
        String contents = random("contents");
        newValue(tags, contents);

        assertEquals(contents, testObject.get(tags));
    }

    String random(String prefix) {
        return prefix + hashCode();
    }

    @Test
    public void get_key_returns_contents_of_2_tagged_values_when_they_have_been_set() {
        newValue("moons", "Io Europa Titan Callisto");
        newValue("planets", "Jupiter Saturn");

        assertEquals("Io Europa Titan Callisto",testObject.get("moons"));
        assertEquals("Jupiter Saturn",testObject.get("planets"));
    }

    TaggedValue newValue(String tags, String contents) {
        TaggedValue taggedValue = testObject.newValue();
        taggedValue.setTags(ScreenTags.of(tags));
        taggedValue.setContents(contents);
        return taggedValue;
    }

    @Test
    public void get_key_returns_null_when_no_value_has_been_set_for_simple_tag() {
        String key = "moons";

        String actual = testObject.get(key);

        String expected = null;
        assertEquals(expected,actual);
    }

}