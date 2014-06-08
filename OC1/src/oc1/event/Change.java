package oc1.event;

/**
 *
 * @author Curt
 */
public final class Change {
    
    public interface Listener {
        void onChange();
    }
    
    public interface Source {
        void addListener(Listener listener);
    }
}
