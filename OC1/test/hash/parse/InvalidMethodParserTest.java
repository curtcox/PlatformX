package hash.parse;

import hash.Expression;
import hash.HashLines;
import hash.Method;
import hash.SyntaxError;
import static hash.SyntaxError.Type.INVALID_METHOD_BODY;
import static hash.SyntaxError.Type.INVALID_METHOD_NAME;
import static hash.SyntaxError.Type.INVALID_METHOD_PARAMS;
import static hash.SyntaxError.Type.MALFORMED_METHOD;
import hash.lex.Tokens;
import org.junit.Test;
import static org.junit.Assert.*;

public class InvalidMethodParserTest {

    InvalidMethodParser testObject = new InvalidMethodParser();
    
    @Test
    public void parse_method_body_with_syntax_error() {
        Method method = Method("foo",new SyntaxError("foo{?}","{?}",INVALID_METHOD_BODY));
        parse("foo{?}",method);
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
        Method actual = testObject.parse(lines(original));
        assertEquals(expected,actual);
    }

    Method Method(String name,Expression expression) {
        return new Method(name,expression);
    }

    private Tokens lines(String...lines) {
        return Tokens.from(HashLines.from(lines));
    }

}
