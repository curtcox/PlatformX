package hash;

public final class NumericConstant 
    implements Expression
{
    final Long value;
    
    public NumericConstant(long value) {
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
        NumericConstant that = (NumericConstant) o;
        return value.equals(that.value);
    }
    
    @Override
    public String toString() {
        return value.toString();
    }
}
