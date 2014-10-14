package hash;

public final class StringConstant 
    implements Expression
{
    final String value;
    
    public StringConstant(String value) {
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
        StringConstant that = (StringConstant) o;
        return value.equals(that.value);
    }
    
    @Override
    public String toString() {
        return value;
    }
}
