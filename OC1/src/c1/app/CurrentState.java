package c1.app;

import common.Registry;
import common.event.Change;
import java.util.ArrayList;
import java.util.List;

/**
 * The current state of the application.
 * @author Curt
 */
public final class CurrentState
    implements Change.Source
{
    private final List<Change.Listener> listeners = new ArrayList<Change.Listener>();
    
    public static final CurrentState get() {
        return Registry.get(CurrentState.class);
    }

    public void addListener(Change.Listener listener) {
        listeners.add(listener);
    }

    public void broadcastChange() {
        for (Change.Listener listener : listeners) {
            listener.onChange();
        }
    }
}
