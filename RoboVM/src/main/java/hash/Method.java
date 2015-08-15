package hash;

/**
 * A method definition.
 * Contrast with Invocation.
 * @author Curt
 */
public final class Method
    implements Expression
{

    public final String name;
    public final ArgNames args;
    public final Expression body;
    
    public Method(String name,Expression body) {
        this(name,new ArgNames(),body);
    }

    public Method(String name,ArgNames args, Expression body) {
        this.name = name;
        this.args = args;
        this.body = body;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Method that = (Method) o;
        return name.equals(that.name) &&
               args.equals(that.args) &&
               body.equals(that.body);
    }

    public Object invokeIn(Context context) {
        return body.invokeIn(context.withArgNames(name,args));
    }

    @Override
    public String toString() {
        return name + args.toString() + body;
    }
}
