package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class SimpleInvokableTest {

    String name;
    String arg1;
    String arg2;
    SimpleInvokable testObject = new SimpleInvokable(name,arg1,arg2) {

        @Override
        public Object invoke(Object[] args) {
            return args;
        }
    };

    @Test
    public void constructor_uses_values() {
        assertSame(name,testObject.name);
        assertSame(arg1,testObject.argNames[0]);
        assertSame(arg2,testObject.argNames[1]);
    }
    
    @Test
    public void newContext_returns_context_with_matching_invokable() {
        Context context = SimpleInvokable.newContext(testObject);
        assertSame(testObject,context.get(name));
    }
}
