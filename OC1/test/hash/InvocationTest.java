package hash;

import java.util.HashMap;
import java.util.Map;
import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class InvocationTest {

    @Test
    public void equals_returns_true_for_invocations_with_the_same_values() {
        assertAreEqual(new Invocation(""),new Invocation(""));
        assertAreEqual(new Invocation("f",new Args(new Invocation("x"))),new Invocation("f",new Args(new Invocation("x"))));
    }

    @Test
    public void equals_returns_false_for_invocations_with_different_values() {
        assertNotEquals(new Invocation("a"),new Invocation("b"));
        assertNotEquals(new Invocation("f",new Args(new Invocation("x"))),new Invocation("f",new Args(new Invocation("y"))));
    }

    private void assertAreEqual(Invocation a, Invocation b) {
        assertEquals(a,b);
        assertEquals(b,a);
        assertEquals(a.hashCode(),b.hashCode());
    }

    private void assertNotEquals(Invocation a, Invocation b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void toString_contains_invocation_name() {
        String string = new Invocation("evars").toString();
        assertTrue(string,Strings.contains(string,"evars"));
    }

    @Test
    public void toString_contains_arg_names() {
        String string = new Invocation("evars",new Args(new Invocation("tinker"),new Invocation("chance"))).toString();
        assertTrue(string,Strings.contains(string,"tinker"));
        assertTrue(string,Strings.contains(string,"chance"));
    }

    @Test
    public void invokeIn_invokes_invokable_from_context_with_invocation_arg_values() {
        String value = "value1";
        StringConstant arg = new StringConstant(value);
        Expression[] args = new Expression[] { arg };
        Invocation invocation = new Invocation("invokable",new Args(args));
        
        Context context = (Context) invocation.invokeIn(Context());
        Expression[] values = context.values;
        assertEquals(1,values.length);
        assertSame(value,values[0].invokeIn(context));
    }

    private Context Context() {
        Expression invokable = new Expression() {
            public Object invokeIn(hash.Context context) {
                return context;
            }
        };
        Map<String,Expression> map = new HashMap<String,Expression>();
        map.put("invokable", invokable);
        return new Context("#",map);
    }
}
