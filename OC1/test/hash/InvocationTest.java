package hash;

import oc1.util.Strings;
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
        assertEquals(new Invocation("f",new Args(new Invocation("x"))),new Invocation("f",new Args(new Invocation("x"))));
    }

    @Test
    public void equals_returns_false_for_invocations_with_different_values() {
        assertNotEquals(new Invocation("a"),new Invocation("b"));
        assertNotEquals(new Invocation("f",new Args(new Invocation("x"))),new Invocation("f",new Args(new Invocation("y"))));
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
        parse(new Invocation("foo"),"foo()");
        parse(new Invocation("f",new Args(new Invocation("x"))),"f(x)");
        parse(new Invocation("f",new Args(new Invocation("x"),new Invocation("y"))),"f(x y)");
    }

    @Test
    public void parse_consumes_trailing_parens() {
        assertFalse(parse(new Invocation("foo"),"foo()").hasNext());
        assertFalse(parse(new Invocation("f",new Args(new Invocation("x"))),"f(x)").hasNext());
        assertFalse(parse(new Invocation("f",new Args(new Invocation("x"),new Invocation("y"))),"f(x y)").hasNext());
    }
    
    private Tokens parse(Invocation invocation,String string) {
        Tokens tokens = Tokens.from(string);
        assertEquals(invocation,new Invocation.Parser().parse(tokens));
        return tokens;
    }
    
    @Test
    public void can_parse_invocations() {
        Invocation.Parser parser = new Invocation.Parser();
        assertTrue(parser.canParse(Tokens.from("invocation")));
        assertTrue(parser.canParse(Tokens.from("doit")));
        assertTrue(parser.canParse(Tokens.from("stuff()")));
        assertTrue(parser.canParse(Tokens.from("f(x)")));
        assertTrue(parser.canParse(Tokens.from("f(x y)")));
        assertTrue(parser.canParse(Tokens.from("f }")));
    }

    @Test
    public void can_not_parse_non_invocations() {
        Invocation.Parser parser = new Invocation.Parser();
        assertFalse(parser.canParse(Tokens.from("\"constant\"")));
        assertFalse(parser.canParse(Tokens.from("?")));
        assertFalse(parser.canParse(Tokens.from(":")));
        assertFalse(parser.canParse(Tokens.from(".")));
        assertFalse(parser.canParse(Tokens.from("{")));
        assertFalse(parser.canParse(Tokens.from("}")));
        assertFalse(parser.canParse(Tokens.from("^")));
        assertFalse(parser.canParse(Tokens.from("f(^)")));
        assertFalse(parser.canParse(Tokens.from("f(}")));
    }

    @Test
    public void toString_contains_invocation_name() {
        String string = new Invocation("evars").toString();
        assertTrue(string,Strings.contains(string,"evars"));
    }

    @Test
    public void toString_contains_arg_names() {
        String string = new Invocation("evars",new Args(new Invocation("tinker"),new Invocation("chance"))).toString();
        assertTrue(string,Strings.contains(string,"tinker"));
        assertTrue(string,Strings.contains(string,"chance"));
    }

}
