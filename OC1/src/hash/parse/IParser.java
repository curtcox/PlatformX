package hash.parse;

import hash.lex.Tokens;

interface IParser {
    boolean canParse(Tokens tokens);
    Object parse(Tokens tokens);
}
