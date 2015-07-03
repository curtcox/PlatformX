package x.util;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import x.page.PageTags;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class GlobTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void star_matches_all_strings() {
        Glob glob = Glob.of("*");
        assertTrue(glob.matches(""));
        assertTrue(glob.matches("red"));
        assertTrue(glob.matches("STUFF"));
    }

    @Test
    public void strings_matches_if_only_case_differs() {
        assertTrue(Glob.of("APPLE").matches("apple"));
        assertTrue(Glob.of("pie").matches("Pie"));
    }

    @Test
    public void string_does_not_match_if_differs_other_than_case() {
        assertFalse(Glob.of("A").matches("B"));
    }

    @Test
    public void matches_tags_when_tag_screen_matches() {
        assertTrue(Glob.of("APPLE").matches(PageTags.of("apple")));
        assertFalse(Glob.of("red").matches(PageTags.of("green")));
    }
}