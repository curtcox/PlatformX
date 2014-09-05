package hash;

/**
 *
 * @author Curt
 */
public final class Constant 
    extends Expression
{

    static final class Parser {
        Constant parse(Tokens tokens) {
            verifyQuote(tokens.next());
            String value = tokens.next();
            if (value.equals("\"")) {
                return new Constant("");
            }
            verifyQuote(tokens.next());
            return new Constant(value);
        }    

        private void verifyQuote(String string) {
            if (!string.equals("\"")) {
                throw new IllegalArgumentException();
            }
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
