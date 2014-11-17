package hash.parse;

import hash.lex.Tokens;

/**
 * Something that might be able to parse some tokens.
 * @author Curt
 */
interface IParser {
    /**
     * Return true, if the given tokens can be parsed, but don't consume any.
     */
    boolean canParse(Tokens tokens);
    
    /**
     * Consume any tokens that can be parsed and return the resulting object.
     */
    Object parse(Tokens tokens);
}
