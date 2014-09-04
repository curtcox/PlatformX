package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class HashTest {
    
    @Test
    public void equals_returns_true_for_hashes_with_the_same_methods() {
        assertEquals(new Hash(),new Hash());
    }

    private void assertEquals(Hash a, Hash b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }
    
}
