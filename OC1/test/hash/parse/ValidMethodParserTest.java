package hash.parse;

import hash.*;
import hash.lex.Tokens;
import static org.junit.Assert.*;
import org.junit.Test;

public class ValidMethodParserTest {
    
    final ValidMethodParser parser = new ValidMethodParser();
    
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

    private void parse(Method method,String string) {
        assertEquals(method,parser.parse(Tokens.from(string)));
    }

    private boolean canParse(String string) {
        return parser.canParse(Tokens.from(string));
    }

}
