package hash;

import org.junit.Test;
import static org.junit.Assert.*;

public class IdentifierTest {
    
    @Test
    public void isValid_returns_false_for_special_characters() {
        for (String c : Identifier.SPECIAL) {
            assertFalse(Identifier.isValid(c));
        }
    }
    
    @Test
    public void valid() {
        assertTrue(Identifier.isValid("a_with_underscores"));
    }

    @Test
    public void invalid() {
        assertFalse(Identifier.isValid("a with spaces"));
        assertFalse(Identifier.isValid("0"));
        assertFalse(Identifier.isValid("1"));
    }
}
