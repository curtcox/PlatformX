package hash.parse;

import hash.*;
import hash.lex.Tokens;
import java.util.ArrayList;
import java.util.List;
import common.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArgsParserTest {

    ArgsParser parser = new ArgsParser();

    @Test
    public void equals_returns_true_for_params_with_the_same_values() {
        assertEquals(Args("a"),Args("a"));
    }

    @Test
    public void equals_returns_false_for_params_with_different_values() {
        assertNotEquals(Args("a"),Args("b"));
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
        parse(Args(),"()");
        parse(Args("a"),"(a)");
        parse(Args("a","b"),"(a b)");
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

    private void parse(Args constant,String string) {
        assertEquals(constant,parser.parse(Tokens.from(string)));
    }
    
        @Test
    public void toString_contains_params() {
        assertTrue(Strings.contains(Args("nuts").toString(),"nuts"));
    }

    private Args Args(String... strings) {
        List<Expression> expressions = new ArrayList<Expression>();
        for (String string : strings) {
            expressions.add(new Invocation(string));
        }
        return new Args(expressions.toArray(new Expression[0]));
    }
}
