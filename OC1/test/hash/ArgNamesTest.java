package hash;

import hash.lex.Tokens;
import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author curt
 */
public class ArgNamesTest {

    ArgNames.Parser parser = new ArgNames.Parser();

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
    public void parse_returns_correct_value() {
        parse(new ArgNames(),"()");
        parse(new ArgNames("a"),"(a)");
        parse(new ArgNames("a","b"),"(a b)");
    }

    @Test
    public void can_parse_empty_params() {
        assertTrue(parser.canParse(Tokens.from("()")));
    }

    @Test
    public void can_parse_identifier_params() {
        assertTrue(parser.canParse(Tokens.from("(red)")));
        assertTrue(parser.canParse(Tokens.from("(green)")));
    }

    @Test
    public void can_not_parse_non_identifier_params() {
        assertFalse(parser.canParse(Tokens.from("red")));
        assertFalse(parser.canParse(Tokens.from("(?)")));
        assertFalse(parser.canParse(Tokens.from("(})")));
        assertFalse(parser.canParse(Tokens.from("({)")));
    }
    
    @Test
    public void can_not_parse_params_without_parens() {
        assertFalse(parser.canParse(Tokens.from("red")));
        assertFalse(parser.canParse(Tokens.from("red)")));
        assertFalse(parser.canParse(Tokens.from("(red")));
    }

    private void parse(ArgNames constant,String string) {
        assertEquals(constant,parser.parse(Tokens.from(string)));
    }
    
        @Test
    public void toString_contains_params() {
        assertTrue(Strings.contains(new ArgNames("nuts").toString(),"nuts"));
    }
    
}
