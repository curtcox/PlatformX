package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class InvocationTest {
    
    @Test
    public void equals_returns_true_for_invocations_with_the_same_values() {
        assertEquals(new Invocation(""),new Invocation(""));
    }

    @Test
    public void equals_returns_false_for_invocations_with_different_values() {
        assertNotEquals(new Invocation("a"),new Invocation("b"));
    }

    private void assertEquals(Invocation a, Invocation b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(Invocation a, Invocation b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void parse_returns_correct_value() {
        parse(new Invocation("foo"),"foo");
    }
    
    private void parse(Invocation invocation,String string) {
        assertEquals(invocation,new Invocation.Parser().parse(Tokens.from(string)));
    }
    
    @Test
    public void can_parse_invocations() {
        assertTrue(new Invocation.Parser().canParse(Tokens.from("invocation")));
        assertTrue(new Invocation.Parser().canParse(Tokens.from("doit")));
    }

    @Test
    public void can_not_parse_non_invocations() {
        assertFalse(new Invocation.Parser().canParse(Tokens.from("\"constant\"")));
        assertFalse(new Invocation.Parser().canParse(Tokens.from("?")));
        assertFalse(new Invocation.Parser().canParse(Tokens.from(":")));
        assertFalse(new Invocation.Parser().canParse(Tokens.from(".")));
    }

}
