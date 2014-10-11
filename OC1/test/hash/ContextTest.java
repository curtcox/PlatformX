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
public class ContextTest {

    String name = "foo";
    Invokable value = new StringConstant("Galore");
    Args args = new Args(new Invokable[] {value});
    SimpleInvokable invokable = new SimpleInvokable(name) {
        @Override
        public Object invoke(Object[] args) {
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
        Context withArgValues = context
                .withArgValues("#",args)
                .withArgNames("invoked",new ArgNames("foo"));
        assertSame(value,withArgValues.get(name));
    }

    @Test
    public void toString_contains_arg_names() {
        Context testObject = new Context("#",new HashMap())
                .withArgValues("#",args)
                .withArgNames("invoked",new ArgNames("Kitty"));
        assertTrue(Strings.contains(testObject.toString(),"Kitty"));
    }

    @Test
    public void toString_contains_arg_values() {
        Context testObject = new Context("#",new HashMap())
                .withArgValues("#",args);
        assertTrue(Strings.contains(testObject.toString(),"Galore"));
    }

    @Test
    public void toString_contains_invokable_names() {
        Map<String,Invokable> map = new HashMap<String,Invokable>();
        map.put("Plenty",value);
        Context testObject = new Context("#",map);
        assertTrue(Strings.contains(testObject.toString(),"Plenty"));
    }

}
