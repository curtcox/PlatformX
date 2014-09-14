package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ExpressionTest {

    @Test
    public void canParse_constant() {
        assertTrue(canParse("\"\""));
        assertTrue(canParse("\"foo\""));
    }

    @Test
    public void canParse_invocation() {
        assertTrue(canParse("doit"));
    }

    @Test
    public void canParse_nested_invocation() {
        assertTrue(canParse("sin(sqrt(x))"));
    }

    @Test
    public void canParse_ternary() {
        assertTrue(canParse("(should)? do : it"));
    }

    @Test
    public void canParse_number() {
        assertTrue(canParse("42"));
    }

    @Test
    public void canParse_return() {
        assertTrue(canParse("^doit"));
    }

    @Test
    public void canParse_returns_false_for_closing_bracket() {
        assertFalse(canParse("}"));
    }

    @Test
    public void parse_returns_correct_value_for_string_constant() {
        parse(new StringConstant(""),"\"\"");
        parse(new StringConstant("foo"),"\"foo\"");
    }

    @Test
    public void parse_returns_correct_value_for_numeric_constant() {
        parse(new NumericConstant(63130),"63130");
    }

    @Test
    public void parse_returns_correct_value_for_invocation() {
        parse(new Invocation("doit"),"doit");
    }

    @Test
    public void parse_returns_correct_value_for_return_invocation() {
        parse(new Return(new Invocation("doit")),"^doit");
    }

    @Test
    public void parse_returns_correct_value_for_nested_invocation() {
        parse(new Invocation("sin",new Args(new Invocation("sqrt",new Args(new Invocation("x"))))),"sin(sqrt(x))");
    }

    @Test
    public void parse_returns_correct_value_for_ternary() {
        parse(new Ternary(new Invocation("a"),new Invocation("b"),new Invocation("c")),"(a) ? b : c");
    }
    
    private void parse(Expression expression,String string) {
        assertEquals(expression,new Expression.Parser().parse(Tokens.from(string)));
    }

    private boolean canParse(String string) {
        return new Expression.Parser().canParse(Tokens.from(string));
    }

}
