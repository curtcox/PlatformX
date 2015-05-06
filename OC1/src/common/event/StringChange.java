package common.event;

public final class StringChange {

    public static final class Event {
        public final Object source;
        public final String oldValue;
        public final String newValue;

        public Event(Object source, String oldValue, String newValue) {
            this.source = source;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public String toString() {
            return "StringChange new value =" + newValue;
        }
    }

    /**
     * Something that listens for change.
     */
    public interface Listener {
        void onChange(Event event);
    }

    /**
     * A source of changes.
     */
    public interface Source {
        void addListener(Listener listener);
    }
}
