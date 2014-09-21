package hash;

import java.util.Arrays;
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
        System.out.println("Created " + this);
    }

    Invokable get(String name) {
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
            "No value for " + name + " in context " + Arrays.asList(names) + " or args " + invokables.keySet());
    }
    
    private Invokable get0(String name) {
        for (int i=0; i<names.length; i++) {
            if (name.equals(names[i])) {
                return values[i];
            }
        }
        return invokables.get(name);
    }

    Context withArgNames(ArgNames names) {
        return new Context(invokables,names.args,values);
    }

    Context withArgValues(Args values) {
        return new Context(invokables,names,values.args);
    }

    @Override
    public String toString() {
        return " names = " + Arrays.asList(names) +
               " values = " + Arrays.asList(values) + 
               " keys = " + invokables.keySet();
    }
}
