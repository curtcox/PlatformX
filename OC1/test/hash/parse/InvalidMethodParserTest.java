package hash.parse;

import hash.Expression;
import hash.Hash;
import hash.HashLines;
import hash.Method;
import hash.SyntaxError;
import static hash.SyntaxError.Type.INVALID_METHOD_BODY;
import static hash.SyntaxError.Type.INVALID_METHOD_NAME;
import static hash.SyntaxError.Type.INVALID_METHOD_PARAMS;
import static hash.SyntaxError.Type.MALFORMED_METHOD;
import static hash.SyntaxError.Type.MULTIPLE_METHOD_INVOCATIONS;
import hash.lex.Tokens;
import org.junit.Test;
import static org.junit.Assert.*;

public class InvalidMethodParserTest {

    InvalidMethodParser testObject = new InvalidMethodParser();
    
    @Test
    public void canParseTokens_returns_false_when_there_are_no_tokens() {
        assertFalse(testObject.canParseTokens(Tokens.from("")));
    }

    @Test
    public void canParseTokens_returns_true_when_there_are_tokens() {
        assertTrue(testObject.canParseTokens(Tokens.from("x")));
    }
    
    @Test
    public void parse_method_body_with_syntax_error() {
        Method method = Method("foo",new SyntaxError("foo{?}","{?}",INVALID_METHOD_BODY));
        parse("foo{?}",method);
    }

    @Test
    public void parse_method_body_with_multiple_method_invocations() {
        Method method = Method("f",new SyntaxError("f{g h}","{g h}",MULTIPLE_METHOD_INVOCATIONS));
        parse("f{g h}",method);
    }

    @Test
    public void parse_malformed_method() {
        Method method = Method("foo",new SyntaxError("foo}","foo}",MALFORMED_METHOD));
        parse("foo}",method);
    }

    @Test
    public void parse_method_params_with_syntax_error() {
        Method method = Method("foo",new SyntaxError("foo(a,b){}", "(a,b)",INVALID_METHOD_PARAMS));
        parse("foo(a,b){}",method);
    }

    @Test
    public void parse_method_name_with_syntax_error() {
        Method method = Method("f?o",new SyntaxError("f?o{}","f?o",INVALID_METHOD_NAME));
        parse("f?o{}",method);
    }
 
    private void parse(String original,Method expected) {
        Tokens tokens = lines(original);
        Method actual = testObject.parse(tokens);
        assertEquals(expected,actual);
        assertFalse(tokens.hasNext());
    }

    Method Method(String name,Expression expression) {
        return new Method(name,expression);
    }

    private Tokens lines(String...lines) {
        return Tokens.from(HashLines.from(lines));
    }

}
