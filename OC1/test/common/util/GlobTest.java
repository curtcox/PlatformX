package common.util;

import common.screen.ScreenTags;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GlobTest {

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
        assertTrue(Glob.of("APPLE").matches(ScreenTags.of("apple")));
        assertFalse(Glob.of("red").matches(ScreenTags.of("green")));
    }
}