package oc1.screen;

import java.util.HashMap;
import java.util.Map;
import oc1.log.LogManager;

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
        if (value==null) {
            log("No value found for " + key);
        }
        return (value instanceof Getter) ? ((Getter)value).get() : value;
    }
    
    private void log(String message) {
        LogManager.of().getLog(ScreenContext.class).log(message);    
    }

}
