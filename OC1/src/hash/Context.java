package hash;

import java.util.Map;

/**
 *
 * @author Curt
 */
public final class Context {

    final Map<String,Invokable> invokables;
    final String[] names;
    final Invokable[] values;
    
    public Context(Map<String,Invokable> invokables) {
        this(invokables,new String[0],new Invokable[0]);
    }

    private Context(Map<String,Invokable> invokables,String[] names,Invokable[] values) {
        this.invokables = invokables;
        this.names = names;
        this.values = values;
    }

    Invokable get(String name) {
        for (int i=0; i<names.length; i++) {
            if (name.equals(names[i])) {
                return values[i];
            }
        }
        return invokables.get(name);
    }

    Context withArgValues(String[] names, Invokable[] values) {
        return new Context(invokables,names,values);
    }
}
