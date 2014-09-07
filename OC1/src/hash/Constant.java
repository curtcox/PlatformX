package hash;

/**
 *
 * @author Curt
 */
public final class Constant 
    extends Expression
{

    static final class Parser 
        implements IParser
    {
        public Constant parse(Tokens tokens) {
            String value = tokens.next();
            if (!value.startsWith("\"") || !value.endsWith("\"")) {
                throw new IllegalArgumentException();
            }
            return new Constant(value.substring(1,value.length()-1));
        }    

        public boolean canParse(Tokens tokens) {
            Tokens copy = tokens.copy();
            if (!copy.hasNext()) {
                return false;
            }
            String token = copy.next();
            return token.startsWith("\"") && token.endsWith("\"");
        }
    }
    
    final String value;
    
    Constant(String value) {
        this.value = value;
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Constant that = (Constant) o;
        return value.equals(that.value);
    }
}
