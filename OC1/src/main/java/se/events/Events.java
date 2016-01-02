package se.events;

import java.util.HashMap;
import java.util.Map;

/**
 * Global event bus.
 * The bus acts as a layer of indirection, so that clients can listen for events
 * without needing to know who fires them.
 */
public final class Events {

    private Map<Class,Listener>listeners = new HashMap<Class, Listener>();

    public interface Listener {
        void onEvent(Event event);
    }

    public interface Event {}

    public void registerListenerFor(Listener listener, Class clazz) {
        listeners.put(clazz,listener);
    }

    public void post(Event event) {
        Listener listener = listeners.get(event.getClass());
        if (listener!=null) {
            listener.onEvent(event);
        }
    }

}
