package se.util;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import x.page.PageTags;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class SimpleTaggedValueTest {

    SimpleTaggedValue testObject = new SimpleTaggedValue();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
    }

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