package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ContextTest {
    
    @Test
    public void get_returns_named_invokable() {
        String name = "foo";
        SimpleInvokable invokable = new SimpleInvokable(name) {
            @Override
            public Object invoke(Object[] args) {
                return null;
            }
        };
        Context context = SimpleInvokable.newContext(invokable);
        
        assertSame(invokable,context.get(name));
    }
    
}
