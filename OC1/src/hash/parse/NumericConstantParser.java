package hash.parse;

import hash.NumericConstant;
import hash.Number;
import hash.lex.Tokens;

final class NumericConstantParser 
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
