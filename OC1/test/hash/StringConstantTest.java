package hash;

import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class StringConstantTest {
    
    @Test
    public void equals_returns_true_for_constants_with_the_same_values() {
        assertEquals(new StringConstant(""),new StringConstant(""));
    }

    @Test
    public void equals_returns_false_for_constants_with_different_values() {
        assertNotEquals(new StringConstant("a"),new StringConstant("b"));
    }

    private void assertEquals(StringConstant a, StringConstant b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(StringConstant a, StringConstant b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void parse_returns_correct_value() {
        parse(new StringConstant(""),"\"\"");
        parse(new StringConstant("foo"),"\"foo\"");
    }

    @Test
    public void can_parse_constants() {
        assertTrue(new StringConstant.Parser().canParse(Tokens.from("\"\"")));
        assertTrue(new StringConstant.Parser().canParse(Tokens.from("\"red\"")));
    }

    @Test
    public void can_not_parse_non_constants() {
        assertFalse(new StringConstant.Parser().canParse(Tokens.from("red")));
        assertFalse(new StringConstant.Parser().canParse(Tokens.from("?")));
        assertFalse(new StringConstant.Parser().canParse(Tokens.from("}")));
        assertFalse(new StringConstant.Parser().canParse(Tokens.from("{")));
    }
    
    private void parse(StringConstant constant,String string) {
        assertEquals(constant,new StringConstant.Parser().parse(Tokens.from(string)));
    }
    
    @Test
    public void toString_contains_constant() {
        assertTrue(Strings.contains(new StringConstant("nuts").toString(),"nuts"));
    }

}
