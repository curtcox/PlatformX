package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ConstantTest {
    
    @Test
    public void equals_returns_true_for_constants_with_the_same_values() {
        assertEquals(new Constant(""),new Constant(""));
    }

    @Test
    public void equals_returns_false_for_constants_with_different_values() {
        assertNotEquals(new Constant("a"),new Constant("b"));
    }

    private void assertEquals(Constant a, Constant b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(Constant a, Constant b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void parse_returns_correct_value() {
        parse(new Constant(""),"\"\"");
        parse(new Constant("foo"),"\"foo\"");
    }

    @Test
    public void can_parse_constants() {
        assertTrue(new Constant.Parser().canParse(Tokens.from("\"\"")));
        assertTrue(new Constant.Parser().canParse(Tokens.from("\"red\"")));
    }

    @Test
    public void can_not_parse_non_constants() {
        assertFalse(new Constant.Parser().canParse(Tokens.from("red")));
        assertFalse(new Constant.Parser().canParse(Tokens.from("?")));
        assertFalse(new Constant.Parser().canParse(Tokens.from("}")));
        assertFalse(new Constant.Parser().canParse(Tokens.from("{")));
    }
    
    private void parse(Constant constant,String string) {
        assertEquals(constant,new Constant.Parser().parse(Tokens.from(string)));
    }
}
