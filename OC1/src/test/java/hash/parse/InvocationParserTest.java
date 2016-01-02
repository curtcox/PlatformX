package hash.parse;

import config.ShouldRun;
import hash.*;
import hash.lex.Tokens;
import org.junit.Before;
import x.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class InvocationParserTest {

    InvocationParser parser = new InvocationParser();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

    private void assertAreEqual(Invocation a, Invocation b) {
        assertEquals(a,b);
        assertEquals(b,a);
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Test
    public void parse_returns_correct_value() {
        parse(new Invocation("foo"),"foo");
        parse(new Invocation("foo"),"foo()");
        parse(new Invocation("f",new Args(new Invocation("x"))),"f(x)");
        parse(new Invocation("f",new Args(new Invocation("x"),new Invocation("y"))),"f(x y)");
        parse(new Invocation("sin",new Args(new Invocation("sqrt",new Args(new Invocation("x"))))),"sin(sqrt(x))");
    }

    @Test
    public void parse_consumes_trailing_parens() {
        assertFalse(parse(new Invocation("foo"),"foo()").hasNext());
        assertFalse(parse(new Invocation("f",new Args(new Invocation("x"))),"f(x)").hasNext());
        assertFalse(parse(new Invocation("f",new Args(new Invocation("x"),new Invocation("y"))),"f(x y)").hasNext());
    }
    
    private Tokens parse(Invocation invocation,String string) {
        Tokens tokens = Tokens.from(string);
        assertAreEqual(invocation,new InvocationParser().parse(tokens));
        return tokens;
    }
    
    @Test
    public void can_parse_simple_invocations() {
        assertTrue(parser.canParse(Tokens.from("invocation")));
        assertTrue(parser.canParse(Tokens.from("doit")));
    }

    @Test
    public void can_parse_invocations_with_empty_parens() {
        assertTrue(parser.canParse(Tokens.from("stuff()")));
    }

    @Test
    public void can_parse_invocations_with_args() {
        assertTrue(parser.canParse(Tokens.from("f(x)")));
        assertTrue(parser.canParse(Tokens.from("f(x y)")));
    }

    @Test
    public void can_parse_nested_invocations() {
        assertTrue(parser.canParse(Tokens.from("sin(sqrt(x))")));
    }

    @Test
    public void can_parse_invocations() {
        assertTrue(parser.canParse(Tokens.from("f }")));
    }

    @Test
    public void can_not_parse_non_invocations() {
        assertFalse(parser.canParse(Tokens.from("\"constant\"")));
        assertFalse(parser.canParse(Tokens.from("0")));
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
