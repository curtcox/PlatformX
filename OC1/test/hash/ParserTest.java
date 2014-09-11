package hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class ParserTest {

    Parser testObject = new Parser();

    @Test
    public void parse_hash_with_return_foo() {
        Hash hash = Hash(Method("foo", Return(Constant("foo"))));
        parse("foo { ^ \"foo\" }",hash);
    }

    @Test
    public void parse_hash_with_get_layout() {
        Hash hash = Hash(Method(
            "getLayout",
            Return(Ternary(Invocation("portrait"),Invocation("layoutForPortrait"),Invocation("layoutForLandscape"))
        )));
        parse(
            lines(
                "getLayout() {",
	            "^ (portrait) ? layoutForPortrait() : layoutForLandscape()",
                "}"),
            hash
        );
    }
    
    @Test
    public void parse_hash_method_definition_and_invocation_with_arguments() {
        Hash hash = Hash(Method(
            "buttonTo",Args("text","image","leadingTo"),
            Return(Invocation("textAndImageLeadingTo","text","image","leadinTo"))
        ));
        parse(
            lines(
                "buttonTo(text image leadingTo) {",
                    "^ textAndImageLeadingTo(text image leadingTo)",
                "}"),
            hash
        );
    }
    
    @Test
    public void parse_hash_ivocation_with_arguments() {
//layoutForPortraitWithSelectedProvider() {
//    ^ Screen(Grid(2,1), newProviderContainer(), newNavigationContainer());
//}
        fail();
    }
    
    Hash Hash(Method...methods) {
        return new Hash(methods);
    }
   
    Method Method(String name,Expression...expressions) {
        return new Method(name,expressions);
    }

    Method Method(String name,Args params, Expression...expressions) {
        return new Method(name,params,expressions);
    }
    
    Return Return(Expression expression) {
        return new Return(expression);
    }

    Ternary Ternary(Expression condition, Expression pass, Expression fail) {
        return new Ternary(condition,pass,fail);
    }

    Invocation Invocation(String text, String...params) {
        return new Invocation(text,params);
    }

    Args Args(String...params) {
        return new Args(params);
    }

    Constant Constant(String text) {
        return new Constant(text);
    }
    
    private void parse(String original,Hash expected) {
        Hash actual = testObject.parse(original);
        assertEquals(expected,actual);
    }
    
    private String lines(String...lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line + " ");
        }
        return out.toString();
    }
}
