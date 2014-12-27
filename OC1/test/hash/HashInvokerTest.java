package hash;

import hash.parse.HashParser;
import static org.junit.Assert.*;
import static hash.SyntaxError.Type.*;
import common.util.Strings;
import org.junit.Test;

public class HashInvokerTest {

    HashParser parser = new HashParser();

    @Test
    public void invoke_method_body_that_returns_syntax_error() {
        Hash hash = hash("foo{?}");
        Object value = hash.invoke("foo",Args(),Context(hash));
        assertEquals(new SyntaxError("foo { ? }","{ ? }",INVALID_METHOD_BODY),value);
    }

    @Test
    public void invoke_malformed_method_that_returns_syntax_error() {
        Hash hash = hash("foo}");
        Object value = hash.invoke("foo",Args(),Context(hash));
        assertEquals(new SyntaxError("foo }","foo }",MALFORMED_METHOD),value);
    }

    @Test
    public void invoke_method_params_that_returns_syntax_error() {
        Hash hash = hash("foo(a,b){}");
        Object value = hash.invoke("foo",Args(),Context(hash));
        assertEquals(new SyntaxError("foo ( a , b ) { }", "( a , b )",INVALID_METHOD_PARAMS),value);
    }

    @Test
    public void invoke_method_name_that_returns_syntax_error() {
        Hash hash = hash("f?o{}");
        try {
            hash.invoke("foo",Args(),Context(hash));
            fail();
        } catch (RuntimeException e) {
            assertTrue(Strings.contains(e.getMessage(),"foo not found"));
        }
    }

    @Test
    public void invoke_method_that_returns_constant() {
        Hash hash = hash("foo { 'foo' }");
        Object value = hash.invoke("foo",Args(),Context(hash));
        assertEquals("foo",value);
    }

    @Test
    public void invoke_method_with_true_ternary() {
        Hash hash = hash(
            "layout          { (portrait) ? layout_portrait : layout_landscape }",
            "layout_portrait { 'Portrait?' }"
        );
        NamedExpression invokable = new NamedExpression("portrait") {
            public Object invoke(Object[] args) { return true; }
        };
        Object value = hash.invoke("layout",Args(),Context(hash,invokable));
        assertEquals("Portrait?",value);
    }

    @Test
    public void invoke_method_with_false_ternary() {
        Hash hash = hash(
            "layout           { (portrait) ? layout_portrait : layout_landscape }",
            "layout_landscape { 'Landscape!!' }"
        );
        NamedExpression invokable = new NamedExpression("portrait") {
            public Object invoke(Object[] args) { return false; }
        };
        Object value = hash.invoke("layout",Args(),Context(hash,invokable));
        assertEquals("Landscape!!",value);
    }

    @Test
    public void invoke_method_that_is_not_defined_first() {
        Hash hash = hash(
            "first  { 1 }",
            "second { 2 }"
        );
        Object value = hash.invoke("second",Args(),Context(hash));
        assertEquals(2L,value);
    }

    @Test
    public void invoke_method_with_arguments() {
        Hash hash = hash(
            "button(text image to) { textAndImageLeadingTo(text image to) }"
        );
        NamedExpression invokable = new NamedExpression("textAndImageLeadingTo") {
            public Object invoke(Object[] args) {
                return "clickable(" + args[0] + ",img:" + args[1] + "," + args[2] + ")";
            }
        };
        Object value = hash.invoke("button",Args(Const("exciting"),Const("beach"),Const("ftp:neato")),Context(hash,invokable));
        assertEquals("clickable(exciting,img:beach,ftp:neato)",value);
    }
    
    @Test
    public void invoke_nested_with_mixed_arguments() {
        Hash hash = hash(
            "layout     { screen( grid(2 1) provider navigation ) }",
            "provider   { 'Provider!'}",
            "navigation { 'NAV'}"
        );
        NamedExpression screen = new NamedExpression("screen") {
            public Object invoke(Object[] args) {
                return "screen(" + args[0] + " " + args[1] + " " + args[2] + ")";
            }
        };
        NamedExpression grid = new NamedExpression("grid") {
            public Object invoke(Object[] args) {
                return "grid(" + args[0] + " " + args[1] + ")";
            }
        };

        Object value = hash.invoke("layout",Args(),Context(hash,screen,grid));
        assertEquals("screen(grid(2 1) Provider! NAV)",value);
    }

    @Test
    public void invokeIn_uses_values_from_context() {
        Hash hash = hash(
            "provider   { 'Provider!'}",
            "navigation { 'NAV'}"
        );
        NamedExpression screen = new NamedExpression("screen") {
            public Object invoke(Object[] args) {
                return "X11(" + args[0] + " " + args[1] + " " + args[2] + ")";
            }
        };
        NamedExpression grid = new NamedExpression("grid") {
            public Object invoke(Object[] args) {
                return "XxY(" + args[0] + " " + args[1] + ")";
            }
        };

        Invocation invocation = Invocation("screen", 
            Invocation("grid",new NumericConstant(2),new NumericConstant(1)),
            Invocation("provider"),
            Invocation("navigation")
        );

        Object value = invocation.invokeIn(Context(hash,screen,grid));
        assertEquals("X11(XxY(2 1) Provider! NAV)",value);
    }
    
    private Context Context(Hash hash,NamedExpression... invokables) {
        return new Context("#",NamedExpression.namedValues(hash,invokables));
    }

    Invocation Invocation(String name, Expression...args) {
        return new Invocation(name, new Args(args));
    }

    private Hash hash(String...lines) {
        return parse(HashLines.from(lines));    
    }
    
    private Args Args(Expression...expressions) {
        return new Args(expressions);
    }
    
    private StringConstant Const(String string) {
        return new StringConstant(string);
    }
    
    private Hash parse(String original) {
        return parser.parse(original);
    }
    

}
