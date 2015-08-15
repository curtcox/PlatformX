package hash;

/**
 *
 * @author Curt
 */
public final class Ternary
    implements Expression
{
    final Expression condition;
    final Expression pass;
    final Expression fail;
    
    public Ternary(Expression condition, Expression pass, Expression fail) {
        this.condition = condition;
        this.pass = pass;
        this.fail = fail;
    }
    
    public Object invokeIn(Context context) {
        return (Boolean) condition.invokeIn(context)
               ? pass.invokeIn(context) 
               : fail.invokeIn(context);
    }

    @Override
    public int hashCode() {
        return condition.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Ternary that = (Ternary) o;
        return condition.equals(that.condition) &&
               pass.equals(that.pass) &&
               fail.equals(that.fail);
    }

    @Override
    public String toString() {
        return condition.toString();
    }
}
