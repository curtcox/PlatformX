package se.events;

import java.util.HashMap;
import java.util.Map;

/**
 * Global event bus.
 * The bus acts as a layer of indirection, so that clients can listen for events
 * without needing to know who fires them.
 * @author Curt
 */
public final class Events {

    private Listener listener;

    public interface Listener {
        void onEvent(Event event);
    }

    public interface Event {}

    public void registerListenerFor(Listener listener, Class clazz) {
        this.listener = listener;
    }

    public void post(Event event) {
        if (listener!=null) {
            listener.onEvent(event);
        }
    }

}
