package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class IdentifierTest {
    
    @Test
    public void isValid_returns_false_for_special_characters() {
        for (String c : Identifier.SPECIAL) {
            assertFalse(Identifier.isValid(c));
        }
    }
    
}
