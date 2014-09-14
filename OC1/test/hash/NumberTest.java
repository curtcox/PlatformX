package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class NumberTest {
    
    @Test
    public void valid() {
        assertTrue(Number.isValid("1"));
    }

    @Test
    public void invalid() {
        assertFalse(Number.isValid("a"));
    }

}
