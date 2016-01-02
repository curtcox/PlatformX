package x.util;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class GlobStringMapTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void star_matches_all() {
        match("*","");
        match("*","red");
        match("*","green");
    }

    @Test
    public void ordinary_strings_match_themselves() {
        match("","");
        match("red","red");
        match("green","green");
        match("x","x");
    }

    @Test
    public void matching_ignores_case() {
        match("Text","texT");
    }

    @Test
    public void oridinary_strings_do_not_match_different_strings() {
        no_match("a","b");
    }

    private void match(String glob,String string) {
        assertTrue(Glob.of(glob).matches(string));
    }

    private void no_match(String glob,String string) {
        assertFalse(Glob.of(glob).matches(string));
    }

}
