package hash;

import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author curt
 */
public class ArgsTest {
    
    @Test
    public void equals_returns_true_for_params_with_the_same_values() {
        assertEquals(new Args("a"),new Args("a"));
    }

    @Test
    public void equals_returns_false_for_params_with_different_values() {
        assertNotEquals(new Args("a"),new Args("b"));
    }

    private void assertEquals(Args a, Args b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(Args a, Args b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void parse_returns_correct_value() {
        parse(new Args(),"()");
        parse(new Args("a"),"(a)");
        parse(new Args("a","b"),"(a b)");
    }

    @Test
    public void can_parse_empty_params() {
        assertTrue(new Args.Parser().canParse(Tokens.from("")));
    }

    @Test
    public void can_parse_identifier_params() {
        assertTrue(new Args.Parser().canParse(Tokens.from("(red)")));
        assertTrue(new Args.Parser().canParse(Tokens.from("(green)")));
    }

    @Test
    public void can_not_parse_non_identifier_params() {
        assertFalse(new Args.Parser().canParse(Tokens.from("red")));
        assertFalse(new Args.Parser().canParse(Tokens.from("(?)")));
        assertFalse(new Args.Parser().canParse(Tokens.from("(})")));
        assertFalse(new Args.Parser().canParse(Tokens.from("({)")));
    }
    
    @Test
    public void can_not_parse_params_without_parens() {
        assertFalse(new Args.Parser().canParse(Tokens.from("red")));
        assertFalse(new Args.Parser().canParse(Tokens.from("red)")));
        assertFalse(new Args.Parser().canParse(Tokens.from("(red")));
    }

    private void parse(Args constant,String string) {
        assertEquals(constant,new Args.Parser().parse(Tokens.from(string)));
    }
    
        @Test
    public void toString_contains_params() {
        assertTrue(Strings.contains(new Args("nuts").toString(),"nuts"));
    }
    
}
