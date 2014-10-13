package hash;

import hash.lex.Tokens;

/**
 *
 * @author Curt
 */
final class StringConstant 
    implements Expression
{

    static final class Parser 
        extends AbstractParser
    {
        public StringConstant parse(Tokens tokens) {
            String value = tokens.next();
            if (!value.startsWith("\"") || !value.endsWith("\"")) {
                throw new IllegalArgumentException();
            }
            return new StringConstant(value.substring(1,value.length()-1));
        }    

        public boolean canParseTokens(Tokens tokens) {
            if (!tokens.hasNext()) {
                return false;
            }
            String token = tokens.next();
            return token.startsWith("\"") && token.endsWith("\"");
        }
    }
    
    final String value;
    
    StringConstant(String value) {
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
