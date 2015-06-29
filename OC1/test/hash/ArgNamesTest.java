package hash;

import x.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArgNamesTest {

    @Test
    public void equals_returns_true_for_params_with_the_same_values() {
        assertEquals(new ArgNames("a"),new ArgNames("a"));
    }

    @Test
    public void equals_returns_false_for_params_with_different_values() {
        assertNotEquals(new ArgNames("a"),new ArgNames("b"));
    }

    private void assertEquals(ArgNames a, ArgNames b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(ArgNames a, ArgNames b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void toString_contains_params() {
        assertTrue(Strings.contains(new ArgNames("nuts").toString(),"nuts"));
    }
    
}
