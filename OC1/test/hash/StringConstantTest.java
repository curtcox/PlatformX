package hash;

import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class StringConstantTest {
    
    StringConstant.Parser parser = new StringConstant.Parser();
    
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
    public void parse_returns_correct_value() {
        parse(new StringConstant(""),"\"\"");
        parse(new StringConstant("foo"),"\"foo\"");
    }

    @Test
    public void can_parse_constants() {
        assertTrue(parser.canParse(Tokens.from("\"\"")));
        assertTrue(parser.canParse(Tokens.from("\"red\"")));
    }

    @Test
    public void can_not_parse_non_constants() {
        assertFalse(parser.canParse(Tokens.from("red")));
        assertFalse(parser.canParse(Tokens.from("?")));
        assertFalse(parser.canParse(Tokens.from("}")));
        assertFalse(parser.canParse(Tokens.from("{")));
    }
    
    private void parse(StringConstant constant,String string) {
        assertAreEqual(constant,parser.parse(Tokens.from(string)));
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
