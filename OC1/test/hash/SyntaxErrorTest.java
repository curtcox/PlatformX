package hash;

import common.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;
import static hash.SyntaxError.Type.*;

public class SyntaxErrorTest {

    @Test
    public void equals_returns_true_for_params_with_the_same_values() {
        assertEquals(new SyntaxError("a","b",DUPLICATE_METHOD_NAME),new SyntaxError("a","b",DUPLICATE_METHOD_NAME));
    }

    @Test
    public void equals_returns_false_for_params_with_different_values() {
        assertNotEquals(new SyntaxError("a","b",DUPLICATE_METHOD_NAME),new SyntaxError("a","a",DUPLICATE_METHOD_NAME));
        assertNotEquals(new SyntaxError("a","a",DUPLICATE_METHOD_NAME),new SyntaxError("a","a",INVALID_METHOD_NAME));
    }

    private void assertEquals(SyntaxError a, SyntaxError b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(SyntaxError a, SyntaxError b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void toString_contains_params() {
        SyntaxError error = new SyntaxError("nuts","bolts",DUPLICATE_METHOD_NAME);
        assertTrue(Strings.contains(error.toString(),"nuts"));
        assertTrue(Strings.contains(error.toString(),"bolts"));
        assertTrue(Strings.contains(error.toString(),DUPLICATE_METHOD_NAME.toString()));
    }

    @Test
    public void invoke_returns_error_itself() {
        SyntaxError error = new SyntaxError("nuts","bolts",DUPLICATE_METHOD_NAME);
        assertSame(error,error.invokeIn(null));
    }
}
