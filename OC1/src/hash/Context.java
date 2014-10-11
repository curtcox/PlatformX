package hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Context for hash expression evaluation.
 * @author Curt
 */
public final class Context
    implements NamedValueProvider<Expression>
{

    final String name;
    final String[] names;
    final Expression[] values;
    final NamedValueProvider<Expression> invokables;
    
    public Context(String name) {
        this(name,new SimpleNamedValueProvider(new HashMap()),new String[0],new Expression[0]);
    }

    public Context(String name, NamedValueProvider<Expression> invokables) {
        this(name,invokables,new String[0],new Expression[0]);
    }

    public Context(String name, Map<String,Expression> invokables) {
        this(name,new SimpleNamedValueProvider(invokables),new String[0],new Expression[0]);
    }

    private Context(String name,NamedValueProvider<Expression> invokables,String[] names,Expression[] values) {
        this.name = name;
        this.invokables = invokables;
        this.names = names;
        this.values = values;
    }

    public Expression get(String name) {
        verifyThereAreEnoughArgs();
        Expression invokable = get0(name);
        if (invokable==null) {
            throw exceptionForMissing(name);
        }
        return invokable; 
    }

    private void verifyThereAreEnoughArgs() {
        if (names.length>values.length) {
            throw new IllegalArgumentException(
                "Not enough values " + Arrays.asList(values) + " for required " + Arrays.asList(names));
        }
    }
    
    private IllegalArgumentException exceptionForMissing(String name) {
        return new IllegalArgumentException(
            "No value for " + name + " in context " + Arrays.asList(names) + " or args " + invokables);
    }
    
    private Expression get0(String name) {
        for (int i=0; i<names.length; i++) {
            if (name.equals(names[i])) {
                return values[i];
            }
        }
        return invokables.get(name);
    }

    Context withArgNames(String name, ArgNames names) {
        return new Context(name,invokables,names.args,values);
    }

    /**
     * Used at the start of an invocation to provide a new context with the given values
     */
    Context withArgValues(String name, Args values) {
        return new Context(name,invokables,names,values.args);
    }

    @Override
    public String toString() {
        return " name = " + name +
               " names = " + Arrays.asList(names) +
               " values = " + Arrays.asList(values) + 
               " keys = " + invokables;
    }
}
