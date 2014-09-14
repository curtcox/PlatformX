package hash;

/**
 *
 * @author Curt
 */
public final class NumericConstant 
    extends Expression
{

    static final class Parser 
        implements IParser
    {
        public NumericConstant parse(Tokens tokens) {
            String value = tokens.next();
            if (!value.startsWith("\"") || !value.endsWith("\"")) {
                throw new IllegalArgumentException();
            }
            String string = value.substring(1,value.length()-1);
            Long number = Long.parseLong(string);
            return new NumericConstant(number);
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
    
    final Long value;
    
    NumericConstant(long value) {
        this.value = value;
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        NumericConstant that = (NumericConstant) o;
        return value==that.value;
    }
    
    @Override
    public String toString() {
        return value.toString();
    }
}
