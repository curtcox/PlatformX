package x.parse;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

public class TokenizerTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void tokenize_throws_exception_if_given_a_zero_character_separator() {
        try {
            tokenize("stuff to tokenize"," ","");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("All separators must be single characters",e.getMessage());
        }
    }

    @Test
    public void tokenize_throws_exception_if_given_a_multi_character_separator() {
        try {
            tokenize("stuff to tokenize","//");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("All separators must be single characters",e.getMessage());
        }
    }

    @Test
    public void tokenize_returns_right_counts_for_all_delimiters() {
        assertEquals(1, tokenize("=", "=").length);
        assertEquals(1, tokenize(" ", " ").length);
        assertEquals(2, tokenize("  ", " ").length);
        assertEquals(2, tokenize(" =", " ", "=").length);
        assertEquals(3, tokenize("   ", " ").length);
        assertEquals(4, tokenize("    ", " ").length);
    }

    @Test
    public void tokenize_can_handle_100_separators() {
        assertStringOfAllSeparators(100);
    }

    @Test
    public void tokenize_can_handle_1000_separators() {
        assertStringOfAllSeparators(1000);
    }

    @Test
    public void tokenize_can_handle_10000_separators() {
        assertStringOfAllSeparators(10000);
    }

    @Test
    public void tokenize_can_handle_100000_separators() {
        assertStringOfAllSeparators(100000);
    }

    void assertStringOfAllSeparators(int size) {
        String token = "+";
        StringBuilder tokens = new StringBuilder();
        for (int i=0; i<size; i++) {
            tokens.append(token);
        }
        assertEquals(size, tokenize(tokens.toString(), token).length);
    }

    @Test
    public void tokenize_returns_right_counts_for_no_delimiters() {
        assertEquals(0, tokenize("").length);
        assertEquals(1, tokenize("xyz", "=").length);
        assertEquals(1, tokenize("x=y", " ").length);
    }

    @Test
    public void tokenize_returns_right_values_for_x_equals_y() {
        assertEquals("x", tokenize("x=y", "=")[0]);
        assertEquals("=", tokenize("x=y", "=")[1]);
        assertEquals("y", tokenize("x=y", "=")[2]);
    }

    @Test
    public void tokenize_returns_right_values_for_key_equals_value() {
        assertEquals("name", tokenize("name=y", "=")[0]);
        assertEquals("=", tokenize("name=y", "=")[1]);
        assertEquals("value", tokenize("x=value", "=")[2]);
    }

    @Test
    public void tokenize_returns_right_values_for_x_y_z() {
        assertEquals("x", tokenize("x y z", " ")[0]);
        assertEquals(" ", tokenize("x y z", " ")[1]);
        assertEquals("y", tokenize("x y z", " ")[2]);
        assertEquals(" ", tokenize("x y z", " ")[3]);
        assertEquals("z", tokenize("x y z", " ")[4]);
    }

    @Test
    public void tokenize_returns_right_values_when_given_multiple_delimiters() {
        assertEquals("x", tokenize("x y:z", ":", " ")[0]);
        assertEquals(" ", tokenize("x y:z", ":", " ")[1]);
        assertEquals("y", tokenize("x y:z", ":", " ")[2]);
        assertEquals(":", tokenize("x y:z", ":", " ")[3]);
        assertEquals("z", tokenize("x y:z", ":", " ")[4]);
    }

    private String[] tokenize(String string,String... separators) {
        return Tokenizer.tokenize(string, separators);
    }
}
