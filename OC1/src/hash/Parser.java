package hash;

/**
 *
 * @author Curt
 */
public final class Parser {

    Hash parse(String source) {
        Method method = new Method.Parser().parse(Tokens.from(source));
        return new Hash(method);
    }
    
}
