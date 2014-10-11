package hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Context for hash expression evaluation.
 * @author Curt
 */
public final class Context
    implements NamedValueProvider<Invokable>
{

    final String name;
    final String[] names;
    final Invokable[] values;
    final NamedValueProvider<Invokable> invokables;
    
    public Context(String name) {
        this(name,new SimpleNamedValueProvider(new HashMap()),new String[0],new Invokable[0]);
    }

    public Context(String name, NamedValueProvider<Invokable> invokables) {
        this(name,invokables,new String[0],new Invokable[0]);
    }

    public Context(String name, Map<String,Invokable> invokables) {
        this(name,new SimpleNamedValueProvider(invokables),new String[0],new Invokable[0]);
    }

    private Context(String name,NamedValueProvider<Invokable> invokables,String[] names,Invokable[] values) {
        this.name = name;
        this.invokables = invokables;
        this.names = names;
        this.values = values;
    }

    public Invokable get(String name) {
        verifyThereAreEnoughArgs();
        Invokable invokable = get0(name);
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
    
    private Invokable get0(String name) {
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
