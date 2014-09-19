package hash;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class SimpleInvokableTest {

    String name = "name";
    String argName1 = "arg1";
    String argName2 = "arg2";
    StringConstant argValue1 = new StringConstant("value1");
    StringConstant argValue2 = new StringConstant("value2");
    SimpleInvokable testObject = new SimpleInvokable(name,argName1,argName2) {

        @Override
        public Object invoke(Invokable[] args) {
            return args;
        }
    };

    Map<String,Invokable> contextMap = new HashMap<String,Invokable>();
    Context context = new Context(contextMap);

    @Test
    public void constructor_uses_values() {
        assertSame(name,testObject.name);
        assertSame(argName1,testObject.argNames[0]);
        assertSame(argName2,testObject.argNames[1]);
    }
    
    @Test
    public void invokeIn_uses_arg_names_to_lookup_arg_values_in_context() {
        contextMap.put(argName1, argValue1);
        contextMap.put(argName2, argValue2);
        Context contextWithArgs = context.withArgValues(testObject.argNames, new Invokable[] { argValue1, argValue2});
        
        Invokable[] args = (Invokable[]) testObject.invokeIn(contextWithArgs);
        
        assertEquals(2,args.length);
        assertSame(argValue1,args[0]);
        assertSame(argValue2,args[1]);
    }
}
