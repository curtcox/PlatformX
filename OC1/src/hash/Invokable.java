package hash;

/**
 * Something that can be invoked in a context to produce a value.
 * Contrast with Value.
 * @author Curt
 */
public interface Invokable {
    
    Object invokeIn(Context context);
}
