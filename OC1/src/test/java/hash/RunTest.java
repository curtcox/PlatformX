package hash;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class RunTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

    @Test
    public void run_method_that_returns_constant() {
        Object value = Run
            .source(HashLines.from("foo { 'foo' }"))
            .method("foo")
            .namedValues(Context())
            .args();
        assertEquals("foo",value);
    }

    @Test
    public void run_method_with_true_ternary() {
        String source = HashLines.from(
            "layout          { (portrait) ? layout_portrait : layout_landscape }",
            "layout_portrait { 'Portrait?' }"
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

}
