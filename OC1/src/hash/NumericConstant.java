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
            if (!Number.isValid(value)) {
                throw new IllegalArgumentException();
            }
            String string = value.substring(0,value.length());
            Long number = Long.parseLong(string);
            return new NumericConstant(number);
        }    

        public boolean canParse(Tokens tokens) {
            Tokens copy = tokens.copy();
            if (!copy.hasNext()) {
                return false;
            }
            return Number.isValid(copy.next());
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
        return value.equals(that.value);
    }
    
    @Override
    public String toString() {
        return value.toString();
    }
}
