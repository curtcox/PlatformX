package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class TokensTest {
    
    @Test
    public void from_creates_empty_tokens_for_empty_string() {
        Tokens tokens = Tokens.from("");
        assertFalse(tokens.hasNext());
    }

    @Test
    public void from_creates_token_with_one_element_for_one_element() {
        Tokens tokens = Tokens.from("one");
        assertTrue(tokens.hasNext());
        assertEquals("one",tokens.next());
        assertFalse(tokens.hasNext());
    }

}
