package hash;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class ParserTest {

    Parser testObject = new Parser();
    
    @Test
    public void parse_hash_with_get_layout() {
        Hash hash = Hash(Method(
            "getLayout",
            Return(Ternary(Expression("portrait"),Expression("layoutForPortrait"),Expression("layoutForLandscape"))
        )));
        parse(
            lines(
                "getLayout() {",
	            "return (portrait) ? layoutForPortrait() : layoutForLandscape()",
                "}"),
            hash
        );
    }
    
    Hash Hash(Method...methods) {
        return new Hash(methods);
    }
   
    Method Method(String name,Expression...expressions) {
        return new Method(name,expressions);
    }
    
    Return Return(Expression expression) {
        return new Return(expression);
    }

    Ternary Ternary(Expression condition, Expression pass, Expression fail) {
        return new Ternary(condition,pass,fail);
    }

    Expression Expression(String text) {
        return new Expression(text);
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
