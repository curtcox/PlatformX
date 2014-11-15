package oc1.screen;

import java.util.HashMap;
import java.util.Map;

/**
 * The context for a screen.
 */
public final class ScreenContext {

    interface Provider {
        ScreenContext getContext();
    }
    
    private final Map<String,Object> values = new HashMap();
    
    public void put(String key, Object value) {
        values.put(key, value);
    }

    public void putAll(Map<String,Object> map) {
        values.putAll(map);
    }
    
    public Object get(String key) {
        Object value = values.get(key);
        return (value instanceof Getter) ? ((Getter)value).get() : value;
    }
}
