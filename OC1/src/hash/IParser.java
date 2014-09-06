package hash;

/**
 *
 * @author Curt
 */
interface IParser {
    boolean canParse(Tokens tokens);
    Object parse(Tokens tokens);
}
