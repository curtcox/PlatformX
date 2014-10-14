package hash;

import java.util.HashMap;
import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

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
