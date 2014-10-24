package hash;

import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * For methods that Hash inherits from Object. 
 */
public class HashObjectMethodsTest {
    
    @Test
    public void equals_returns_true_for_hashes_with_the_same_methods() {
        assertEquals(new Hash(),new Hash());
    }

    @Test
    public void equals_returns_false_for_hashes_with_different_methods() {
        assertNotEquals(new Hash(Method("a")),new Hash());
        assertNotEquals(new Hash(Method("a")),new Hash(Method("b")));
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
        assertTrue(Strings.contains(new Hash(Method("nuts")).toString(),"nuts"));
    }
    
    private Method Method(String name) {
        return new Method(name,Expression.EMPTY);
    }
}
