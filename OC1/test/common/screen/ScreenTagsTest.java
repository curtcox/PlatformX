package common.screen;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

    @Test
    public void are_equal() {
        assertEqual(ScreenTags.of(""), ScreenTags.of(""));
        assertEqual(ScreenTags.of("x"), ScreenTags.of("x"));
    }

    private void assertEqual(ScreenTags a, ScreenTags b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Test
    public void are_not_equal() {
        assertNotEqual(ScreenTags.of("x"), ScreenTags.of("y"));
    }

    private void assertNotEqual(ScreenTags a, ScreenTags b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

}