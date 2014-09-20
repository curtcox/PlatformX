package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ContextTest {

    String name = "foo";
    SimpleInvokable invokable = new SimpleInvokable(name) {
        @Override
        public Object invoke(Invokable[] args) {
            return null;
        }
    };
    Context context = SimpleInvokable.newContext(new Hash(),invokable);
    
    @Test
    public void get_returns_named_invokable_from_context() {
        assertSame(invokable,context.get(name));
    }

    @Test
    public void get_returns_arg_value_before_constructor_value() {
        Invokable value = new StringConstant("");
        Context withArgValues = context.withArgValues(new String[] {"foo"},new Invokable[] {value});
        assertSame(value,withArgValues.get(name));
    }

}
