package se.util;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import x.event.StringSource;
import x.page.PageTags;
import x.util.StringMap;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class SimpleTaggedValueStringMapTest {

    SimpleTaggedValueStringMap testObject = new SimpleTaggedValueStringMap();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
    }

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
        assertTrue(testObject.newValue(PageTags.of("")) instanceof TaggedValue);
    }

    @Test
    public void get_key_returns_contents_of_tagged_value_with_simple_tag_when_value_has_been_set() {
        String tags = random("tags");
        String contents = random("contents");
        newValue(tags, contents);

        assertEquals(contents, testObject.get(tags));
    }

    @Test
    public void get_tags_returns_contents_of_tagged_value_with_simple_tag_when_value_has_been_set() {
        String tags = random("tags");
        String contents = random("contents");
        newValue(tags, contents);

        StringSource[] actual = testObject.get(PageTags.of(tags));

        assertEquals(1,actual.length);
        assertEquals(contents, actual[0].getString());
    }

    @Test
    public void getValuesFor_returns_contents_of_tagged_value_with_simple_tag_when_value_has_been_set() {
        String tags = random("tags");
        String contents = random("contents");
        newValue(tags, contents);

        TaggedValue[] actual = testObject.getValuesFor(PageTags.of(tags));

        assertEquals(1,actual.length);
        assertEquals(contents, actual[0].getContents());
        assertEquals(PageTags.of(tags), actual[0].getTags());
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
        MutableTaggedValue taggedValue = (MutableTaggedValue) testObject.newValue(PageTags.of(tags));
        taggedValue.setContents(contents);
        return taggedValue;
    }

    @Test
    public void get_key_returns_null_when_no_value_has_been_set_for_simple_tag() {
        String actual = testObject.get("moons");

        String expected = null;
        assertEquals(expected,actual);
    }

    @Test
    public void getValuesFor_returns_empty_array_when_no_value_has_been_set_for_simple_tag() {
        TaggedValue[] actual = testObject.getValuesFor(PageTags.of("moons"));

        assertEquals(0,actual.length);
    }

    @Test
    public void get_tags_returns_empty_array_when_no_value_has_been_set_for_simple_tag() {
        StringSource[] actual = testObject.get(PageTags.of("moons"));

        assertEquals(0,actual.length);
    }

}