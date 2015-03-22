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

    interface Listener {
        void onEvent(Event event);
    }

    interface Event {}

    private final Map<Class,Object> listeners = new HashMap<Class,Object>();

    public void registerListenerFor(Listener listener, Class clazz) {

    }

    public void post(Event event) {

    }

}
