package hash.parse;

import hash.Return;
import hash.lex.Tokens;

final class ReturnParser
    extends AbstractParser
{
    public Return parse(Tokens tokens) {
        verifyReturn(tokens.next());
        return new Return(new ExpressionParser().parse(tokens));
    }    

    private void verifyReturn(String string) {
        if (!string.equals("^")) {
            throw new IllegalArgumentException();
        }
    }

    public boolean canParseTokens(Tokens tokens) {
        return tokens.nextIs("^");
    }
}
