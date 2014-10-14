package hash.parse;

import hash.lex.Tokens;

/**
 *
 * @author Curt
 */
interface IParser {
    boolean canParse(Tokens tokens);
    Object parse(Tokens tokens);
}
