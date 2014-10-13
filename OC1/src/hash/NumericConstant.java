package hash;

import hash.lex.Tokens;

/**
 *
 * @author Curt
 */
final class NumericConstant 
    implements Expression
{

    static final class Parser 
        extends AbstractParser
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

        public boolean canParseTokens(Tokens tokens) {
            if (!tokens.hasNext()) {
                return false;
            }
            return Number.isValid(tokens.next());
        }
    }
    
    final Long value;
    
    NumericConstant(long value) {
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
