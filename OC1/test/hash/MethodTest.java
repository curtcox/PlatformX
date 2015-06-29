package hash;

import java.util.HashMap;
import x.util.Strings;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class MethodTest {
    
    final Context[] expressionResult = new Context[1]; 
    final Expression expression = new Expression() {
        public Object invokeIn(Context context) {
            expressionResult[0] = context;
            return expressionResult;
        }
    };
    
    @Test
    public void equals_returns_true_for_methods_with_the_same_values() {
        assertEquals(new Method("",Expression.EMPTY),new Method("",Expression.EMPTY));
        assertEquals(new Method("a",Expression.EMPTY),new Method("a",Expression.EMPTY));
        assertEquals(new Method("b",new StringConstant("!")),new Method("b",new StringConstant("!")));
        assertEquals(new Method("b",new ArgNames("a"),Expression.EMPTY),new Method("b",new ArgNames("a"),Expression.EMPTY));
    }

    @Test
    public void equals_returns_false_for_methods_with_different_values() {
        assertNotEquals(new Method("a",Expression.EMPTY),new Method("b",Expression.EMPTY));
        assertNotEquals(new Method("b",new StringConstant("!")),new Method("b",new StringConstant("?")));
        assertNotEquals(new Method("f",new ArgNames("x"),Expression.EMPTY),new Method("f",new ArgNames("y"),Expression.EMPTY));
    }

    private void assertEquals(Method a, Method b) {
        Assert.assertEquals(a,b);
        Assert.assertEquals(b,a);
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(Method a, Method b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    
    @Test
    public void toString_contains_name() {
        assertTrue(Strings.contains(new Method("nuts",Expression.EMPTY).toString(),"nuts"));
    }

    @Test
    public void toString_contains_expressions() {
        String string = new Method("tinker",new Invocation("evars")).toString();
        assertTrue(string,Strings.contains(string,"evars"));
    }

    @Test
    public void toString_contains_args() {
        String string = new Method("tinker",new ArgNames("evars","chance"),Expression.EMPTY).toString();
        assertTrue(string,Strings.contains(string,"evars"));
        assertTrue(string,Strings.contains(string,"chance"));
    }

    @Test
    public void invokeIn_invokes_expression_with_context_and_values_and_returns_result_for_method_with_0_args() {
        Context context = new Context("#",new HashMap());
        String[] names = new String[0];
        ArgNames args = new ArgNames(names);
        Method method = new Method("name",args,expression);
        
        Context[] result = (Context[]) method.invokeIn(context);
        
        assertSame(result,expressionResult);
        assertSame(names,result[0].names);
    }

    @Test
    public void invokeIn_invokes_expression_with_context_and_values_and_returns_result_for_method_with_1_arg() {
        Context context = new Context("#",new HashMap());
        String[] names = new String[] { "x" };
        ArgNames args = new ArgNames(names);
        Method method = new Method("f",args,expression);
        
        Context[] result = (Context[]) method.invokeIn(context);
        
        assertSame(result,expressionResult);
        assertSame(names,result[0].names);
    }

}
