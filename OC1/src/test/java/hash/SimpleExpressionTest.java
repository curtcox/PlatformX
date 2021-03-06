package hash;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assume.assumeTrue;

public class SimpleExpressionTest {

    String name = "name";
    StringConstant argValue1 = new StringConstant("value1");
    StringConstant argValue2 = new StringConstant("value2");
    NamedExpression testObject = new NamedExpression(name) {

        @Override
        public Object invoke(Object[] args) {
            return args;
        }
    };

    Map<String,Expression> contextMap = new HashMap<String,Expression>();
    Context context = new Context("#",contextMap);

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

    @Test
    public void constructor_uses_values() {
        assertSame(name,testObject.name);
    }
    
    @Test
    public void invokeIn_uses_context_args() {
        Context contextWithArgs = context.withArgValues("#",new Args(new Expression[] { argValue1, argValue2}));
        
        Object[] args = (Object[]) testObject.invokeIn(contextWithArgs);
        
        assertEquals(2,args.length);
        assertSame("value1",args[0]);
        assertSame("value2",args[1]);
    }
}
