package x.util;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class StringsTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void split_returns_right_counts_for_no_delimiters() {
        assertEquals(1,Strings.split("",     "").length);
        assertEquals(1,Strings.split("xyz", "=").length);
        assertEquals(1,Strings.split("x=y", " ").length);
    }

    @Test
    public void split_returns_right_values_for_x_equals_y() {
        assertEquals("x",Strings.split("x=y","=")[0]);
        assertEquals("y",Strings.split("x=y","=")[1]);
    }

    @Test
    public void split_returns_right_values_for_key_equals_value() {
        assertEquals("key",Strings.split("key=y","=")[0]);
        assertEquals("value",Strings.split("x=value","=")[1]);
    }

    @Test
    public void split_returns_right_values_for_x_y_z() {
        assertEquals("x",Strings.split("x y z"," ")[0]);
        assertEquals("y",Strings.split("x y z"," ")[1]);
        assertEquals("z",Strings.split("x y z"," ")[2]);
    }

    @Test
    public void replace_returns_original_when_text_not_in_string() {
        assertEquals("fox",Strings.replace("fox", "_", "?"));
    }

    @Test
    public void replace_replaced_text_with_replacement() {
        assertEquals("what the fox",Strings.replace("what_the_fox", "_", " "));
    }

    @Test
    public void isEmpty() {
        assertTrue(Strings.isEmpty(""));
        assertFalse(Strings.isEmpty("!"));
    }

    @Test
    public void elided_is_limited_to_first_100_characters() {
        String string = random("1") + random("2") + random("3") +random("4");
        assertTrue(string.length()>100);
        String actual = Strings.elided(string);

        assertEquals(100, actual.length());
        assertTrue(actual.endsWith("..."));
        assertEquals(string.substring(0,97),actual.substring(0,97));
    }

    @Test
    public void elided_is_same_when_short_enough() {
        String string = random("1");
        assertTrue(string.length()<90);
        String actual = Strings.elided(string);

        assertEquals(string,actual);
    }

    private String random(String prefix) {
        return prefix + toString();
    }
}
