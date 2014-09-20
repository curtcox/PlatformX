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

    static Context newContext(Hash hash,SimpleInvokable... invokables) {
        Map<String,Invokable> map = new HashMap<String,Invokable>();
        for (SimpleInvokable invokable : invokables) {
            map.put(invokable.name, invokable);
        }
        for (Method method : hash.methods) {
            map.put(method.name, method);
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
        return invoke(context.values);
    }

    public abstract Object invoke(Invokable[] named);
}
