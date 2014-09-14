package hash;

import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class NumericConstantTest {
    
    @Test
    public void equals_returns_true_for_constants_with_the_same_values() {
        assertEquals(new NumericConstant(0),new NumericConstant(0));
    }

    @Test
    public void equals_returns_false_for_constants_with_different_values() {
        assertNotEquals(new NumericConstant(1),new NumericConstant(2));
    }

    private void assertEquals(NumericConstant a, NumericConstant b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(NumericConstant a, NumericConstant b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void parse_returns_correct_value() {
        parse(new NumericConstant(7),"7");
        parse(new NumericConstant(42),"42");
    }

    @Test
    public void can_parse_constants() {
        assertTrue(new NumericConstant.Parser().canParse(Tokens.from("9")));
        assertTrue(new NumericConstant.Parser().canParse(Tokens.from("10")));
    }

    @Test
    public void can_not_parse_non_constants() {
        assertFalse(new NumericConstant.Parser().canParse(Tokens.from("red")));
        assertFalse(new NumericConstant.Parser().canParse(Tokens.from("?")));
        assertFalse(new NumericConstant.Parser().canParse(Tokens.from("}")));
        assertFalse(new NumericConstant.Parser().canParse(Tokens.from("{")));
    }
    
    private void parse(NumericConstant constant,String string) {
        assertEquals(constant,new NumericConstant.Parser().parse(Tokens.from(string)));
    }
    
    @Test
    public void toString_contains_costant() {
        assertTrue(Strings.contains(new NumericConstant(300).toString(),"300"));
    }

}
