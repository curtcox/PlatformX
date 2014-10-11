package hash;

/**
 * Something that can be invoked in a context to produce a value.
 * Contrast with Value.
 * @author Curt
 */
public interface Expression {
    
    static final Expression EMPTY = new Expression() {
        public Object invokeIn(Context context) {
            return null;
        }
        
        @Override
        public String toString() {
            return "{}";
        }
    };

    Object invokeIn(Context context);
}
