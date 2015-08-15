package hash.parse;

import hash.StringConstant;
import hash.lex.Tokens;

final class StringConstantParser 
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
