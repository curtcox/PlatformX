package hash;

import hash.Tokenizer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class TokenizerTest {
    
    @Test
    public void tokenize_returns_right_counts_for_all_delimiters() {
        assertEquals(1,Tokenizer.tokenize("=", "=").length);
        assertEquals(1,Tokenizer.tokenize(" ", " ").length);
        assertEquals(2,Tokenizer.tokenize("  ", " ").length);
        assertEquals(2,Tokenizer.tokenize(" =", " ","=").length);
        assertEquals(3,Tokenizer.tokenize("   ", " ").length);
        assertEquals(4,Tokenizer.tokenize("    ", " ").length);
    }

    @Test
    public void tokenize_returns_right_counts_for_no_delimiters() {
        assertEquals(0,Tokenizer.tokenize("").length);
        assertEquals(1,Tokenizer.tokenize("xyz", "=").length);
        assertEquals(1,Tokenizer.tokenize("x=y", " ").length);
    }

    @Test
    public void tokenize_returns_right_values_for_x_equals_y() {
        assertEquals("x",Tokenizer.tokenize("x=y","=")[0]);
        assertEquals("=",Tokenizer.tokenize("x=y","=")[1]);
        assertEquals("y",Tokenizer.tokenize("x=y","=")[2]);
    }

    @Test
    public void tokenize_returns_right_values_for_key_equals_value() {
        assertEquals("key",Tokenizer.tokenize("key=y","=")[0]);
        assertEquals("=",Tokenizer.tokenize("key=y","=")[1]);
        assertEquals("value",Tokenizer.tokenize("x=value","=")[2]);
    }

    @Test
    public void tokenize_returns_right_values_for_x_y_z() {
        assertEquals("x",Tokenizer.tokenize("x y z"," ")[0]);
        assertEquals(" ",Tokenizer.tokenize("x y z"," ")[1]);
        assertEquals("y",Tokenizer.tokenize("x y z"," ")[2]);
        assertEquals(" ",Tokenizer.tokenize("x y z"," ")[3]);
        assertEquals("z",Tokenizer.tokenize("x y z"," ")[4]);
    }

    @Test
    public void tokenize_returns_right_values_when_given_multiple_delimiters() {
        assertEquals("x",Tokenizer.tokenize("x y:z",":"," ")[0]);
        assertEquals(" ",Tokenizer.tokenize("x y:z",":"," ")[1]);
        assertEquals("y",Tokenizer.tokenize("x y:z",":"," ")[2]);
        assertEquals(":",Tokenizer.tokenize("x y:z",":"," ")[3]);
        assertEquals("z",Tokenizer.tokenize("x y:z",":"," ")[4]);
    }

}
