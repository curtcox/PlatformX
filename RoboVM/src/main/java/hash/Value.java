package hash;

/**
 * Something that has a value.  Contrast with Expression.
 * @author Curt
 */
public final class Value
    implements Expression
{
    final Object value;

    private Value(Object value) {
        this.value = value;
    }

    public static Value of(Object value) {
        return value==null ? null : new Value(value);    
    }
    
    public Object invokeIn(Context context) {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Value that = (Value) o;
        return value.equals(that.value);
    }
    
    @Override
    public String toString() {
        return value.toString();
    }
    
}
