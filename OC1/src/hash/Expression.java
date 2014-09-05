package hash;

/**
 *
 * @author Curt
 */
public class Expression {

    static final class Parser {
        Expression parse(Tokens tokens) {
            return new Constant.Parser().parse(tokens);
        }    
    }
}
