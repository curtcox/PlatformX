package hash;

import oc1.util.Strings;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author curt
 */
public class TernaryTest {
    
    @Test
    public void equals_returns_true_for_methods_with_the_same_values() {
        assertEquals(new Ternary(new Constant("a"),new Constant("b"),new Constant("c")),
                     new Ternary(new Constant("a"),new Constant("b"),new Constant("c")));
    }

    @Test
    public void equals_returns_false_for_methods_with_different_values() {
        assertNotEquals(new Ternary(new Constant("a"),new Constant("b"),new Constant("c")),
                        new Ternary(new Constant("a"),new Constant("b"),new Constant("d")));
    }

    private void assertEquals(Ternary a, Ternary b) {
        Assert.assertEquals(a,b);
        Assert.assertEquals(b,a);
        assertTrue(a.hashCode()==b.hashCode());
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
        parse(new Ternary(new Invocation("a"),new Constant("b"),new Constant("c")),"(a) ? \"b\" : \"c\"");
    }

    private void parse(Ternary ternary,String string) {
        assertEquals(ternary,new Ternary.Parser().parse(Tokens.from(string)));
    }

    private boolean canParse(String string) {
        return new Ternary.Parser().canParse(Tokens.from(string));
    }
    
    @Test
    public void toString_contains_condition() {
        assertTrue(Strings.contains(new Ternary(new Constant("nuts"),new Constant(""),new Constant("")).toString(),"nuts"));
    }

}
