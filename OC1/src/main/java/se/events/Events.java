package se.events;

import org.w3c.dom.DOMStringList;

import java.util.*;

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

    public Collection<Listener> getListenersFor(Class clazz) {
        Listener listener = listeners.get(clazz);
        return (listener==null)
            ? Collections.EMPTY_LIST
            : Collections.singleton(listeners.get(clazz));
    }

    public void post(Event event) {
        Listener listener = listeners.get(event.getClass());
        if (listener!=null) {
            listener.onEvent(event);
        }
    }

}
