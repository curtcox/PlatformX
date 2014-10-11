package hash;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Curt
 */
abstract class SimpleExpression
    implements Expression
{

    static Context newContext(Hash hash,SimpleExpression... invokables) {
        Map<String,Expression> map = new HashMap<String,Expression>();
        for (SimpleExpression invokable : invokables) {
            map.put(invokable.name, invokable);
        }
        for (Method method : hash.methods) {
            map.put(method.name, method);
        }
        return new Context("#",map);        
    }

    static Context newContext(SimpleExpression... invokables) {
        Map<String,Expression> map = new HashMap<String,Expression>();
        for (SimpleExpression invokable : invokables) {
            map.put(invokable.name, invokable);
        }
        return new Context("#",map);        
    }

    final String name;
    
    SimpleExpression(String name) {
        this.name = name;
    }

    public Object invokeIn(Context context) {
        Object[] values = new Object[context.values.length];
        for (int i=0; i<values.length; i++) {
            values[i] = context.values[i].invokeIn(context);
        }
        return invoke(values);
    }

    @Override
    public String toString() {
        return name;
    }
    
    public abstract Object invoke(Object[] values);
}
