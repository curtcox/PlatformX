package hash;

import java.util.HashMap;
import java.util.Map;
import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class InvocationTest {

    Invocation.Parser parser = new Invocation.Parser();
    
    @Test
    public void equals_returns_true_for_invocations_with_the_same_values() {
        assertAreEqual(new Invocation(""),new Invocation(""));
        assertAreEqual(new Invocation("f",new Args(new Invocation("x"))),new Invocation("f",new Args(new Invocation("x"))));
    }

    @Test
    public void equals_returns_false_for_invocations_with_different_values() {
        assertNotEquals(new Invocation("a"),new Invocation("b"));
        assertNotEquals(new Invocation("f",new Args(new Invocation("x"))),new Invocation("f",new Args(new Invocation("y"))));
    }

    private void assertAreEqual(Invocation a, Invocation b) {
        assertEquals(a,b);
        assertEquals(b,a);
        assertEquals(a.hashCode(),b.hashCode());
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
        assertAreEqual(invocation,new Invocation.Parser().parse(tokens));
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

    @Test
    public void invokeIn_invokes_invokable_from_context_with_invocation_arg_values() {
        StringConstant arg = new StringConstant("value1");
        Expression[] args = new Expression[] { arg };
        Invocation invocation = new Invocation("invokable",new Args(args));
        
        Context context = (Context) invocation.invokeIn(Context());
        Invokable[] values = context.values;
        assertEquals(1,values.length);
        assertSame(arg,values[0]);
    }

    private Context Context() {
        Invokable invokable = new Invokable() {
            public Object invokeIn(hash.Context context) {
                return context;
            }
        };
        Map<String,Invokable> map = new HashMap<String,Invokable>();
        map.put("invokable", invokable);
        return new Context(map);
    }
}
