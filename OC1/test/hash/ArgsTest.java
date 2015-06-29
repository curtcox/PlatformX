package hash;

import java.util.ArrayList;
import java.util.List;
import x.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArgsTest {

    @Test
    public void equals_returns_true_for_params_with_the_same_values() {
        assertEquals(Args("a"),Args("a"));
    }

    @Test
    public void equals_returns_false_for_params_with_different_values() {
        assertNotEquals(Args("a"),Args("b"));
    }

    private void assertEquals(Args a, Args b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(Args a, Args b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void toString_contains_params() {
        assertTrue(Strings.contains(Args("nuts").toString(),"nuts"));
    }

    private Args Args(String... strings) {
        List<Expression> expressions = new ArrayList<Expression>();
        for (String string : strings) {
            expressions.add(new Invocation(string));
        }
        return new Args(expressions.toArray(new Expression[0]));
    }
}
