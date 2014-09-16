package hash;

import java.util.Map;

/**
 *
 * @author Curt
 */
public final class Context {

    final Map<String,Invokable> invokables;
    
    public Context(Map<String,Invokable> invokables) {
        this.invokables = invokables;
    }

    Object get(String name) {
        return invokables.get(name);
    }
}
