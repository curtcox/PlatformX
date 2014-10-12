package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class RunTest {
    
    @Test
    public void run_method_that_returns_constant() {
        Object value = Run
            .source(lines("foo { ^ 'foo' }"))
            .method("foo")
            .namedValues(Context())
            .args();
        assertEquals("foo",value);
    }

    @Test
    public void run_method_with_true_ternary() {
        String source = lines(
            "layout          { ^ (portrait) ? layout_portrait : layout_landscape }",
            "layout_portrait { ^ 'Portrait?' }"
        );
        NamedExpression invokable = new NamedExpression("portrait") {
            public Object invoke(Object[] args) { return true; }
        };
        Object value = Run
            .source(source)
            .method("layout")
            .namedValues(Context(invokable))
            .args();
        assertEquals("Portrait?",value);
    }

    private NamedValues Context(NamedExpression... expressions) {
        return NamedExpression.namedValues(expressions);
    }

    private String lines(String...lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line + " \r\n");
        }
        return out.toString().replaceAll("'", "\"");
    }

}
