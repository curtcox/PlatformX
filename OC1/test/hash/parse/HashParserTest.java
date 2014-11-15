package hash.parse;

import hash.ArgNames;
import hash.Args;
import hash.Expression;
import hash.Hash;
import hash.HashLines;
import hash.Invocation;
import hash.Method;
import hash.NumericConstant;
import hash.StringConstant;
import hash.SyntaxError;
import static hash.SyntaxError.Type.*;
import hash.Ternary;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HashParserTest {

    HashParser testObject = new HashParser();

        @Test
    public void parse_method_body_with_syntax_error() {
        Hash hash = Hash(Method("foo",new SyntaxError("foo { ? }","{ ? }",INVALID_METHOD_BODY)));
        parse("foo { ? }",hash);
    }

    @Test
    public void parse_malformed_method() {
        Hash hash = Hash(Method("foo",new SyntaxError("foo }","foo }",MALFORMED_METHOD)));
        parse("foo }",hash);
    }

    @Test
    public void parse_method_params_with_syntax_error() {
        Hash hash = Hash(Method("foo",new SyntaxError("foo(a,b) { }", "(a,b)",INVALID_METHOD_PARAMS)));
        parse("foo(a,b) { }",hash);
    }

    @Test
    public void parse_method_name_with_syntax_error() {
        Hash hash = Hash(Method("",new SyntaxError("f?o { }","f?o",INVALID_METHOD_NAME)));
        parse("f?o { }",hash);
    }

    @Test
    public void parse_hash_with_return_foo() {
        Hash hash = Hash(Method("foo", Constant("foo")));
        parse("foo { 'foo' }",hash);
    }

    @Test
    public void parse_hash_with_get_layout() {
        Hash hash = Hash(Method(
            "getLayout",
            Ternary(Invocation("portrait"),Invocation("layoutForPortrait"),Invocation("layoutForLandscape")
        )));
        parse(
            lines(
                "getLayout() {",
	            " (portrait) ? layoutForPortrait() : layoutForLandscape()",
                "}"),
            hash
        );
    }
    
    @Test
    public void parse_hash_method_definition_and_invocation_with_arguments() {
        Hash hash = Hash(Method(
            "buttonTo",ArgNames("text","image","leadingTo"),
                Invocation("textAndImageLeadingTo",
                    Args(Invocation("text"),Invocation("image"),Invocation("leadingTo"))))
        );
        parse(
            lines(
                "buttonTo(text image leadingTo) {",
                    " textAndImageLeadingTo(text image leadingTo)",
                "}"),
            hash
        );
    }
    
    @Test
    public void parse_hash_nested_invocation_with_mixed_arguments() {
        Hash hash = Hash(Method(
            "layoutForPortraitWithSelectedProvider",ArgNames(),
                   Invocation("Screen",
                       Args(
                           Invocation("Grid",Args(2,1)),
                           Invocation("newProviderContainer"),
                           Invocation("newNavigationContainer")) )
        ));
        parse(
            lines(
                "layoutForPortraitWithSelectedProvider() {",
                     " Screen(Grid(2 1) newProviderContainer() newNavigationContainer())",
                "}"),
            hash
        );
    }

    @Test
    public void parse_hash_with_two_methods() {
        Hash hash = Hash(
            Method("layout",ArgNames(),Ternary(Invocation("portrait"),Invocation("layout_portrait"),Invocation("layout_landscape"))),
            Method("layout_landscape",ArgNames(),Constant("Landscape!!"))
        );
        parse(
            lines(
                "layout           { (portrait) ? layout_portrait : layout_landscape }",
                "layout_landscape { 'Landscape!!' }"
            ),
            hash
        );
    }
    
    Hash Hash(Method...methods) {
        return new Hash(methods);
    }
   
    Method Method(String name,Expression expression) {
        return new Method(name,expression);
    }

    Method Method(String name,ArgNames params, Expression expression) {
        return new Method(name,params,expression);
    }
    
    Ternary Ternary(Expression condition, Expression pass, Expression fail) {
        return new Ternary(condition,pass,fail);
    }

    Invocation Invocation(String text, Args args) {
        return new Invocation(text,args);
    }

    Invocation Invocation(String text) {
        return new Invocation(text,new Args());
    }

    Args Args(Expression...params) {
        return new Args(params);
    }

    Args Args(long...args) {
        List<NumericConstant> list = new ArrayList<NumericConstant>();
        for (long arg : args) {
            list.add(NumericConstant(arg));
        }
        return new Args(list.toArray(new NumericConstant[0]));
    }

    ArgNames ArgNames(String...params) {
        return new ArgNames(params);
    }

    StringConstant Constant(String text) {
        return new StringConstant(text);
    }

    NumericConstant NumericConstant(long value) {
        return new NumericConstant(value);
    }
    
    private void parse(String original,Hash expected) {
        Hash actual = testObject.parse(lines(original));
        assertEquals(expected,actual);
    }
    
    private String lines(String...lines) {
        return HashLines.from(lines);
    }
}
