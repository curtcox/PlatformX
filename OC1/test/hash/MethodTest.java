package hash;

import java.util.HashMap;
import oc1.util.Strings;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class MethodTest {
    
    final Context[] expressionResult = new Context[1]; 
    final Expression expression = new Expression() {
        public Object invokeIn(Context context) {
            expressionResult[0] = context;
            return expressionResult;
        }
    };
    final Method.Parser parser = new Method.Parser();
    
    @Test
    public void equals_returns_true_for_methods_with_the_same_values() {
        assertEquals(new Method("",Expression.EMPTY),new Method("",Expression.EMPTY));
        assertEquals(new Method("a",Expression.EMPTY),new Method("a",Expression.EMPTY));
        assertEquals(new Method("b",new StringConstant("!")),new Method("b",new StringConstant("!")));
        assertEquals(new Method("b",new ArgNames("a"),Expression.EMPTY),new Method("b",new ArgNames("a"),Expression.EMPTY));
    }

    @Test
    public void equals_returns_false_for_methods_with_different_values() {
        assertNotEquals(new Method("a",Expression.EMPTY),new Method("b",Expression.EMPTY));
        assertNotEquals(new Method("b",new StringConstant("!")),new Method("b",new StringConstant("?")));
        assertNotEquals(new Method("f",new ArgNames("x"),Expression.EMPTY),new Method("f",new ArgNames("y"),Expression.EMPTY));
    }

    private void assertEquals(Method a, Method b) {
        Assert.assertEquals(a,b);
        Assert.assertEquals(b,a);
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(Method a, Method b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void canParse_empty_method() {
        assertTrue(canParse("x{}"));
    }

    @Test
    public void parsing_a_method_consumes_all_of_its_tokens() {
        Tokens tokens = Tokens.from("x{}");
        parser.parse(tokens);
        assertFalse(tokens.toString(),tokens.hasNext());
    }

    @Test
    public void canParse_returns_true_when_there_are_methods_left_to_parse() {
        Tokens tokens = Tokens.from("x{} y{}");
        assertTrue(tokens.toString(),parser.canParse(tokens));
        parser.parse(tokens);
        assertTrue(tokens.toString(),parser.canParse(tokens));
        parser.parse(tokens);
        assertFalse(tokens.toString(),parser.canParse(tokens));
    }

    @Test
    public void canParse_method_with_empty_params() {
        assertTrue(canParse("x(){}"));
    }

    @Test
    public void canParse_method_with_one_param() {
        assertTrue(canParse("f(x){}"));
    }

    @Test
    public void canParse_method_with_two_params() {
        assertTrue(canParse("f(x y){}"));
    }

    @Test
    public void canParse_method_with_constant() {
        assertTrue(canParse("x{ \"word\" }"));
    }

    @Test
    public void canParse_method_with_invocation() {
        assertTrue(canParse("x{ stuff }"));
    }
    
    @Test
    public void parse_returns_correct_value_for_empty_method() {
        parse(new Method("x",Expression.EMPTY),"x{}");
    }

    @Test
    public void parse_returns_correct_values_for_multiple_methods() {
        Tokens tokens = Tokens.from("x{} y{}");
        assertEquals(new Method("x",Expression.EMPTY),parser.parse(tokens));
        assertEquals(new Method("y",Expression.EMPTY),parser.parse(tokens));
    }

    @Test
    public void parse_returns_correct_value_for_method_with_empty_params() {
        parse(new Method("x",Expression.EMPTY),"x(){}");
    }

    @Test
    public void parse_returns_correct_value_for_method_with_one_param() {
        parse(new Method("f",new ArgNames("x"),Expression.EMPTY),"f(x){}");
    }

    @Test
    public void parse_returns_correct_value_for_method_with_two_param() {
        parse(new Method("f",new ArgNames("x", "y"),Expression.EMPTY),"f(x y){}");
    }

    @Test
    public void parse_returns_correct_value_for_method_with_constant() {
        parse(new Method("x",new StringConstant("word")),"x{ \"word\" }");
    }

    @Test
    public void parse_returns_correct_value_for_method_with_invocation() {
        parse(new Method("x",new Invocation("stuff")),"x{ stuff }");
    }

    @Test
    public void parse_returns_correct_value_for_method_with_return() {
        parse(new Method("x",new Return(new StringConstant("word"))),"x{ ^ \"word\" }");
    }

    private void parse(Method method,String string) {
        assertEquals(method,parser.parse(Tokens.from(string)));
    }

    private boolean canParse(String string) {
        return parser.canParse(Tokens.from(string));
    }
    
    @Test
    public void toString_contains_name() {
        assertTrue(Strings.contains(new Method("nuts",Expression.EMPTY).toString(),"nuts"));
    }

    @Test
    public void toString_contains_expressions() {
        String string = new Method("tinker",new Invocation("evars")).toString();
        assertTrue(string,Strings.contains(string,"evars"));
    }

    @Test
    public void toString_contains_args() {
        String string = new Method("tinker",new ArgNames("evars","chance"),Expression.EMPTY).toString();
        assertTrue(string,Strings.contains(string,"evars"));
        assertTrue(string,Strings.contains(string,"chance"));
    }

    @Test
    public void invokeIn_invokes_expression_with_context_and_returns_result() {
        Context context = new Context(new HashMap());
        Method method = new Method("name",expression);
        
        Context[] result = (Context[]) method.invokeIn(context);
        assertSame(result,expressionResult);
        assertSame(result[0],context);
    }
    
}
