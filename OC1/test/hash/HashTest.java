package hash;

import oc1.util.Strings;
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

    @Test
    public void equals_returns_false_for_hashes_with_different_methods() {
        assertNotEquals(new Hash(new Method("a")),new Hash());
        assertNotEquals(new Hash(new Method("a")),new Hash(new Method("b")));
    }

    private void assertEquals(Hash a, Hash b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(Hash a, Hash b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    @Test
    public void toString_contains_method_name() {
        assertTrue(Strings.contains(new Hash(new Method("nuts")).toString(),"nuts"));
    }
}
