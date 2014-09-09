package hash;

/**
 *
 * @author Curt
 */
public final class Invocation 
    extends Expression
{

    static final class Parser
        implements IParser
    {
        public Invocation parse(Tokens tokens) {
            String value = tokens.next();
            if (!Identifier.isValid(value)) {
                throw new IllegalArgumentException();
            }
            return new Invocation(value);
        }    

        public boolean canParse(Tokens tokens) {
            Tokens copy = tokens.copy();
            return copy.hasNext() && Identifier.isValid(copy.next());
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
    
    @Override
    public String toString() {
        return value;
    }
}
