package common.event;

public final class Change {
    
    public interface Listener {
        void onChange();
    }
    
    public interface Source {
        void addListener(Listener listener);
    }
}
