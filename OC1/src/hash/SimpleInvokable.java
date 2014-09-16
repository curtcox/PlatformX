package hash;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Curt
 */
public abstract class SimpleInvokable
    implements Invokable
{

    static Context newContext(SimpleInvokable... invokables) {
        Map map = new HashMap();
        for (SimpleInvokable invokable : invokables) {
            map.put(invokable.name, invokable);
        }
        return new Context(map);        
    }

    final String name;
    final String[] argNames;
    
    SimpleInvokable(String name, String... argNames) {
        this.name = name;
        this.argNames = argNames;
    }

    public Object invokeIn(Context context) {
        Object[] args = null;
        return invoke(args);
    }
    
    public abstract Object invoke(Object[] args);
}
