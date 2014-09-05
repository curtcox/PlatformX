package hash;

/**
 *
 * @author Curt
 */
public final class Invocation 
    extends Expression
{

    static final class Parser {
        Invocation parse(Tokens tokens) {
            String value = tokens.next();
            return new Invocation(value);
        }    
    }
    
    final String value;
    
    Invocation(String value) {
        this.value = value;
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Invocation that = (Invocation) o;
        return value.equals(that.value);
    }
}
