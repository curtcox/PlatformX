package hash;

import java.util.HashMap;
import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ReturnTest {
    
    @Test
    public void equals_returns_true_for_returns_with_the_same_values() {
        assertEquals(new Return(new StringConstant("")),new Return(new StringConstant("")));
    }

    @Test
    public void equals_returns_false_for_returns_with_different_values() {
        assertNotEquals(new Return(new StringConstant("a")),new Return(new StringConstant("b")));
    }

    private void assertEquals(Return a, Return b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(Return a, Return b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void parse_returns_correct_value_for_constants() {
        parse(new Return(new StringConstant("")),"^ \"\"");
        parse(new Return(new StringConstant("foo")),"^ \"foo\"");
    }

    @Test
    public void parse_returns_correct_value_for_invocations() {
        parse(new Return(new Invocation("foo")),"^ foo");
    }

    @Test
    public void canParse_returns_true_when_starts_with_return() {
        assertTrue(canParse("^ foo"));
    }

    @Test
    public void canParse_returns_false_when_does_not_start_with_return() {
        assertFalse(canParse(""));
        assertFalse(canParse("?"));
        assertFalse(canParse(":"));
    }
    
    private void parse(Return ret,String string) {
        assertEquals(ret,new Return.Parser().parse(Tokens.from(string)));
    }

    private boolean canParse(String string) {
        return new Return.Parser().canParse(Tokens.from(string));
    }

    @Test
    public void toString_contains_return_character() {
        assertTrue(Strings.contains(new Return(new StringConstant("")).toString(),"^"));
    }

    @Test
    public void toString_contains_expression() {
        assertTrue(Strings.contains(new Return(new StringConstant("nuts")).toString(),"nuts"));
    }

    @Test
    public void invokeIn_returns_result_from_constant_expression() {
        Context context = new Context("#",new HashMap());
        String value = "You don't say.";
        Return testObject = new Return(new StringConstant(value));
        
        Object result = testObject.invokeIn(context);
        
        assertSame(value,result);
    }

    @Test
    public void invokeIn_invokes_expression_with_context() {
        Context context = new Context("#",new HashMap());
        Expression expression = new Expression() {
            public Object invokeIn(Context context) {
                return context;
            }
        };
        Return testObject = new Return(expression);
        
        Object result = testObject.invokeIn(context);
        
        assertSame(context,result);
    }

}
