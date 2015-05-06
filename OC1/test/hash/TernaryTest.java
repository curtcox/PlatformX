package hash;

import java.util.HashMap;
import java.util.Map;
import common.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

public class TernaryTest {
    
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
    public void toString_contains_condition() {
        assertTrue(Strings.contains(new Ternary(new StringConstant("nuts"),new StringConstant(""),new StringConstant("")).toString(),"nuts"));
    }

    @Test
    public void invokeIn_returns_pass_constant() {
        Ternary ternary = new Ternary(new Invocation("if"),new NumericConstant(7L),new NumericConstant(9L));
        Object value = ternary.invokeIn(Context(true));
        assertEquals(value,7L);
    }

    @Test
    public void invokeIn_returns_fail_constant() {
        Ternary ternary = new Ternary(new Invocation("if"),new NumericConstant(7L),new NumericConstant(9L));
        Object value = ternary.invokeIn(Context(false));
        assertEquals(value,9L);
    }

    private Context Context(final boolean condition) {
        Map<String,Expression> map = new HashMap<String,Expression>();
        NamedExpression invokable = new NamedExpression("if") {
            public Object invoke(Object[] args) {
                return condition;
            }
        };
        map.put("if", invokable);
        return new Context("#",map);
    }
}
