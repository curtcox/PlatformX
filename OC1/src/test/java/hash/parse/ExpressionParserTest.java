package hash.parse;

import config.ShouldRun;
import hash.*;
import hash.lex.Tokens;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class ExpressionParserTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

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
    public void canParse_nested_invocation_with_multiple_args_at_each_level() {
        assertTrue(canParse("screen( grid(2 1) provider navigation )"));
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
        parse(Invocation("doit"),"doit");
    }

    @Test
    public void parse_returns_correct_value_for_nested_invocation() {
        parse(
            Invocation("sin",Invocation("sqrt",Invocation("x"))),
            "sin(sqrt(x))"
        );
    }

    @Test
    public void parse_returns_correct_value_for_nested_invocation_with_multiple_args_at_each_level() {
        parse(
            Invocation("screen",
                Invocation("grid",new NumericConstant(2),new NumericConstant(1)),
                Invocation("provider"),
                Invocation("navigation")
            ),
            "screen( grid(2 1) provider navigation )"
        );
    }
    
    @Test
    public void parse_returns_correct_value_for_ternary() {
        parse(new Ternary(new Invocation("a"),new Invocation("b"),new Invocation("c")),"(a) ? b : c");
    }
    
    private void parse(Expression expression,String string) {
        assertEquals(expression,new ExpressionParser().parse(Tokens.from(string)));
    }

    private boolean canParse(String string) {
        return new ExpressionParser().canParse(Tokens.from(string));
    }

    Invocation Invocation(String name, Expression...args) {
        return new Invocation(name, new Args(args));
    }
}
