package hash.parse;

import hash.parse.IParser;
import hash.lex.Tokens;

abstract class AbstractParser
    implements IParser
{
    public final boolean canParse(Tokens tokens) {
        return canParseTokens(tokens.copy());
    }

    public abstract boolean canParseTokens(Tokens tokens);
}
