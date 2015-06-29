package hash;

import x.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class StringConstantTest {
    
    @Test
    public void equals_returns_true_for_constants_with_the_same_values() {
        assertAreEqual(new StringConstant(""),new StringConstant(""));
    }

    @Test
    public void equals_returns_false_for_constants_with_different_values() {
        assertNotEquals(new StringConstant("a"),new StringConstant("b"));
    }

    private void assertAreEqual(StringConstant a, StringConstant b) {
        assertEquals(a,b);
        assertEquals(b,a);
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(StringConstant a, StringConstant b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void toString_contains_constant() {
        assertTrue(Strings.contains(new StringConstant("nuts").toString(),"nuts"));
    }

    @Test
    public void invokeIn_returns_constant_value() {
        assertEquals("foo",new StringConstant("foo").invokeIn(null));
        assertEquals("bar",new StringConstant("bar").invokeIn(null));
    }
}
