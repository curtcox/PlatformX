package se.util;

import common.page.PageTags;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SimpleTaggedValueTest {

    SimpleTaggedValue testObject = new SimpleTaggedValue();

    @Test
    public void is_tagged_value() {
        assertTrue(new SimpleTaggedValue() instanceof TaggedValue);
    }

    @Test
    public void get_tags_returns_value_set() {
        PageTags tags = PageTags.of("stuff");
        testObject.setTags(tags);

        assertSame(tags, testObject.getTags());
    }

    @Test
    public void get_contents_returns_value_set() {
        String contents = "text stuff";
        testObject.setContents(contents);

        assertSame(contents, testObject.getContents());
    }

}