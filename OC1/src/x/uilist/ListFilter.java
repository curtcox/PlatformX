package x.uilist;

public interface ListFilter<T> {
    
    ListFilter ALLOW_ALL = new ListFilter() {
        public boolean passes(Object item) {
            return true;
        }
    };

    ListFilter ALLOW_NONE = new ListFilter() {
        public boolean passes(Object item) {
            return false;
        }
    };

    boolean passes(T item);
}
