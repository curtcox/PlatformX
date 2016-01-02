package hash;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class IdentifierTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

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
