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
        return new Context("#",map);        
    }

    final String name;
    
    SimpleInvokable(String name) {
        this.name = name;
    }

    public Object invokeIn(Context context) {
        Object[] values = new Object[context.values.length];
        for (int i=0; i<values.length; i++) {
            values[i] = context.values[i].invokeIn(context);
        }
        return invoke(values);
    }

    public abstract Object invoke(Object[] values);
}
