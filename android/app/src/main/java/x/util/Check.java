package x.util;

/**
 * For checking argument values.
 * @author Curt
 */
public final class Check {
    
    public static <T> T notNull(T t) {
        if (t==null) {
            throw new NullPointerException();
        }
        return t;
    }

    public static <T> T notNull(T t,String message) {
        if (t==null) {
            throw new NullPointerException(message);
        }
        return t;
    }

}
