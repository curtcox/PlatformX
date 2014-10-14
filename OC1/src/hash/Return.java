package hash;

public final class Return
    implements Expression
{
    final Expression expression;
    
    public Return(Expression expression) {
        this.expression = expression;
    }
    
    public Object invokeIn(Context context) {
        return expression.invokeIn(context);
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Return that = (Return) o;
        return expression.equals(that.expression);
    }
    
    @Override
    public String toString() {
        return "^" + expression;
    }
}
