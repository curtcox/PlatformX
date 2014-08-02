package oc1.uilist;

/**
 *
 * @author Curt
 */
public interface ListFilter<T> {
    
    public static ListFilter ALLOW_ALL = new ListFilter() {
        public boolean passes(Object item) {
            return true;
        }
    };
    
    boolean passes(T item);
}
