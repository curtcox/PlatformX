package oc1.screen;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Curt
 */
public final class ScreenContext {

    private final Map<String,Object> values = new HashMap();
    
    public void put(String key, Object value) {
        values.put(key, value);
    }
    
    public Object get(String key) {
        Object value = values.get(key);
        return (value instanceof Getter) ? ((Getter)value).get() : value;
    }
}
