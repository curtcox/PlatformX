package hash;

/**
 * Something that has a value.  Contrast with Invokable.
 * @author Curt
 */
public final class Value
    implements Expression
{
    final Object value;

    public Value(Object value) {
        this.value = value;
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
