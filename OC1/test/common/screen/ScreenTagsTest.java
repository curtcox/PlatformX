package common.screen;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScreenTagsTest {

    @Test
    public void can_create() {
        assertTrue(ScreenTags.of("word") instanceof ScreenTags);
    }

    @Test
    public void toString_returns_lowercase_version_of_single_tag() {
        assertEquals("what",ScreenTags.of("what").toString());
        assertEquals("lower",ScreenTags.of("LOWER").toString());
        assertEquals("case",ScreenTags.of("Case").toString());
    }
}