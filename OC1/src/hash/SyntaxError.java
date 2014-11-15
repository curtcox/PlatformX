package hash;

/**
 * For reporting syntax errors to the user.
 * @author Curt
 */
public class SyntaxError {

    public enum Type {
        INVALID_METHOD_BODY,
        MALFORMED_METHOD,
        INVALID_METHOD_PARAMS,
        INVALID_METHOD_NAME,
        DUPLICATE_METHOD_NAME
    }

    SyntaxError(String methodSource, String errorSource, Type type) {
    }
}
