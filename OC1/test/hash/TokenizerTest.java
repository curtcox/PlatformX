package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class TokenizerTest {
    
    @Test
    public void tokenize_returns_right_counts_for_all_delimiters() {
        assertEquals(1,tokenize("=", "=").length);
        assertEquals(1,tokenize(" ", " ").length);
        assertEquals(2,tokenize("  ", " ").length);
        assertEquals(2,tokenize(" =", " ","=").length);
        assertEquals(3,tokenize("   ", " ").length);
        assertEquals(4,tokenize("    ", " ").length);
    }

    @Test
    public void tokenize_returns_right_counts_for_no_delimiters() {
        assertEquals(0,tokenize("").length);
        assertEquals(1,tokenize("xyz", "=").length);
        assertEquals(1,tokenize("x=y", " ").length);
    }

    @Test
    public void tokenize_returns_right_values_for_x_equals_y() {
        assertEquals("x",tokenize("x=y","=")[0]);
        assertEquals("=",tokenize("x=y","=")[1]);
        assertEquals("y",tokenize("x=y","=")[2]);
    }

    @Test
    public void tokenize_returns_right_values_for_key_equals_value() {
        assertEquals("key",  tokenize("key=y","=")[0]);
        assertEquals("=",    tokenize("key=y","=")[1]);
        assertEquals("value",tokenize("x=value","=")[2]);
    }

    @Test
    public void tokenize_returns_right_values_for_x_y_z() {
        assertEquals("x",tokenize("x y z"," ")[0]);
        assertEquals(" ",tokenize("x y z"," ")[1]);
        assertEquals("y",tokenize("x y z"," ")[2]);
        assertEquals(" ",tokenize("x y z"," ")[3]);
        assertEquals("z",tokenize("x y z"," ")[4]);
    }

    @Test
    public void tokenize_returns_right_values_when_given_multiple_delimiters() {
        assertEquals("x",tokenize("x y:z",":"," ")[0]);
        assertEquals(" ",tokenize("x y:z",":"," ")[1]);
        assertEquals("y",tokenize("x y:z",":"," ")[2]);
        assertEquals(":",tokenize("x y:z",":"," ")[3]);
        assertEquals("z",tokenize("x y:z",":"," ")[4]);
    }

    private String[] tokenize(String string,String... tokens) {
        return Tokenizer.tokenize(string, tokens);
    }
}
