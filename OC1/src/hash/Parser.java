package hash;

/**
 *
 * @author Curt
 */
public final class Parser {

    Hash parse(String source) {
        Method method = Method.parse(Tokens.from(source));
        return new Hash(method);
    }
    
}
