package common.event;

public final class Change {

    /**
     * Something that listens for change.
     */
    public interface Listener {
        void onChange();
    }

    /**
     * A source of changes.
     */
    public interface Source {
        void addListener(Listener listener);
    }
}
