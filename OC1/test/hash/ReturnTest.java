package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ReturnTest {
    
    @Test
    public void equals_returns_true_for_returns_with_the_same_values() {
        assertEquals(new Return(new Constant("")),new Return(new Constant("")));
    }

    @Test
    public void equals_returns_false_for_returns_with_different_values() {
        assertNotEquals(new Return(new Constant("a")),new Return(new Constant("b")));
    }

    private void assertEquals(Return a, Return b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(Return a, Return b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void parse_returns_correct_value_for_constants() {
        parse(new Return(new Constant("")),"return \"\"");
        parse(new Return(new Constant("foo")),"return \"foo\"");
    }
    
    private void parse(Return ret,String string) {
        assertEquals(ret,new Return.Parser().parse(Tokens.from(string)));
    }
    
}
