package hash;

/**
 * A named runtime invocation, possibly with arguments.
 * Invocations can be used as part of a method definition.
 * Depending on the number of arguments and the context used for invocation,
 * this could be thought of as method invocation or a variable lookup.
 * @author Curt
 */
public final class Invocation 
    implements Expression
{
    final String name;
    final Args args;
    
    public Invocation(String name) {
        this(name,new Args());
    }

    public Invocation(String name,Args args) {
        this.name = name;
        this.args = args;
    }
    
    public Object invokeIn(Context context) {
        return context.get(name).invokeIn(contextWithArgValues(context));
    }

    private Context contextWithArgValues(Context context) {
        return context.withArgValues(name,args.valuesFor(context));
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Invocation that = (Invocation) o;
        return name.equals(that.name) && args.equals(that.args);
    }
    
    @Override
    public String toString() {
        return name + args;
    }
}
