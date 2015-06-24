package x.page;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PageTagsTest {

    @Test
    public void can_create() {
        assertTrue(PageTags.of("word") instanceof PageTags);
    }

    @Test
    public void toString_returns_lowercase_version_of_single_tag() {
        assertEquals("what", PageTags.of("what").toString());
        assertEquals("lower", PageTags.of("LOWER").toString());
        assertEquals("case", PageTags.of("Case").toString());
    }

    @Test
    public void are_equal() {
        assertEqual(PageTags.of(""), PageTags.of(""));
        assertEqual(PageTags.of("x"), PageTags.of("x"));
    }

    private void assertEqual(PageTags a, PageTags b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Test
    public void are_not_equal() {
        assertNotEqual(PageTags.of("x"), PageTags.of("y"));
    }

    private void assertNotEqual(PageTags a, PageTags b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    @Test
    public void matches_screen_link_when_tags_contains_all_link_tags() {
        assertTrue(tags("").matches(link("")));
        assertTrue(tags("a").matches(link("")));
        assertTrue(tags("r g b").matches(link("r")));
        assertTrue(tags("r g b").matches(link("g b")));
        assertTrue(tags("r g b").matches(link("b g r")));
    }

    @Test
    public void does_not_match_screen_link_when_tags_does_not_contains_all_link_tags() {
        assertFalse(tags("").matches(link("a")));
        assertFalse(tags("g").matches(link("r g b")));
    }

    private PageLink link(String s) {
        return PageLink.of(s);
    }

    private PageTags tags(String s) {
        return PageTags.of(s);
    }
}