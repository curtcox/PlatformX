package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class MethodTest {
    
    @Test
    public void equals_returns_true_for_methods_with_the_same_values() {
        assertEquals(new Method(""),new Method(""));
        assertEquals(new Method("a"),new Method("a"));
        assertEquals(new Method("b",new Constant("!")),new Method("b",new Constant("!")));
    }

    @Test
    public void equals_returns_false_for_methods_with_different_values() {
        assertNotEquals(new Method("a"),new Method("b"));
        assertNotEquals(new Method("b",new Constant("!")),new Method("b",new Constant("?")));
    }

    private void assertEquals(Method a, Method b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(Method a, Method b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void parse_returns_correct_value() {
        parse(new Method("x"),"x{}");
    }
    
    private void parse(Method method,String string) {
        assertEquals(method,new Method.Parser().parse(Tokens.from(string)));
    }
}
