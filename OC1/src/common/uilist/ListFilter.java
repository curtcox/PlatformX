package common.uilist;

public interface ListFilter<T> {
    
    public static ListFilter ALLOW_ALL = new ListFilter() {
        public boolean passes(Object item) {
            return true;
        }
    };

    public static ListFilter ALLOW_NONE = new ListFilter() {
        public boolean passes(Object item) {
            return false;
        }
    };

    boolean passes(T item);
}
