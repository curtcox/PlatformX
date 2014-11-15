package hash;

/**
 * For reporting syntax errors to the user.
 * @author Curt
 */
public final class SyntaxError
    implements Expression
{

    public enum Type {
        INVALID_METHOD_BODY,
        MALFORMED_METHOD,
        INVALID_METHOD_PARAMS,
        INVALID_METHOD_NAME,
        DUPLICATE_METHOD_NAME
    }

    final String methodSource;
    final String errorSource;
    final Type type;
    
    public SyntaxError(String methodSource, String errorSource, Type type) {
        this.errorSource = errorSource;
        this.methodSource = methodSource;
        this.type = type;
    }

    public Object invokeIn(Context context) {
        return this;
    }

    @Override
    public int hashCode() {
        return 0;
    }
    
    @Override
    public boolean equals(Object o) {
        SyntaxError that = (SyntaxError) o;
        return methodSource.equals(that.methodSource) &&
               errorSource.equals(that.errorSource) &&
               type.equals(that.type);
    }
    
    @Override
    public String toString() {
        return methodSource + " " + errorSource + " " + type;
    }
}
