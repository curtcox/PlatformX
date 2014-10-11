package hash;

import java.util.HashMap;
import java.util.Map;
import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author curt
 */
public class TernaryTest {
    
    Ternary.Parser parser =  new Ternary.Parser();
    
    @Test
    public void equals_returns_true_for_methods_with_the_same_values() {
        assertAreEqual(new Ternary(new StringConstant("a"),new StringConstant("b"),new StringConstant("c")),
                       new Ternary(new StringConstant("a"),new StringConstant("b"),new StringConstant("c")));
    }

    @Test
    public void equals_returns_false_for_methods_with_different_values() {
        assertNotEquals(new Ternary(new StringConstant("a"),new StringConstant("b"),new StringConstant("c")),
                        new Ternary(new StringConstant("a"),new StringConstant("b"),new StringConstant("d")));
    }

    private void assertAreEqual(Ternary a, Ternary b) {
        assertEquals(a,b);
        assertEquals(b,a);
        assertEquals(a.hashCode(),b.hashCode());
    }

    private void assertNotEquals(Ternary a, Ternary b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    @Test
    public void canParse_ternary_with_all_invocations() {
        assertTrue(canParse("(a) ? b : c"));
    }

    @Test
    public void canParse_method_with_constant_alternatives() {
        assertTrue(canParse("(pink) ? \"red\" : \"green\""));
    }

    @Test
    public void parse_returns_correct_value_for_all_invocations() {
        parse(new Ternary(new Invocation("a"),new Invocation("b"),new Invocation("c")),"(a) ? b : c");
    }

    @Test
    public void parse_returns_correct_value_for_constant_alternatives() {
        parse(new Ternary(new Invocation("a"),new StringConstant("b"),new StringConstant("c")),"(a) ? \"b\" : \"c\"");
    }

    private void parse(Ternary ternary,String string) {
        assertAreEqual(ternary,parser.parse(Tokens.from(string)));
    }

    private boolean canParse(String string) {
        return parser.canParse(Tokens.from(string));
    }
    
    @Test
    public void toString_contains_condition() {
        assertTrue(Strings.contains(new Ternary(new StringConstant("nuts"),new StringConstant(""),new StringConstant("")).toString(),"nuts"));
    }

    @Test
    public void invokeIn_returns_pass_constant() {
        Ternary ternary = parser.parse(Tokens.from("(if) ? 7 : 9"));
        Object value = ternary.invokeIn(Context(true));
        assertEquals(value,7L);
    }

    @Test
    public void invokeIn_returns_fail_constant() {
        Ternary ternary = parser.parse(Tokens.from("(if) ? 7 : 9"));
        Object value = ternary.invokeIn(Context(false));
        assertEquals(value,9L);
    }

    private Context Context(final boolean condition) {
        Map<String,Expression> map = new HashMap<String,Expression>();
        SimpleExpression invokable = new SimpleExpression("if") {
            public Object invoke(Object[] args) {
                return condition;
            }
        };
        map.put("if", invokable);
        return new Context("#",map);
    }
}
