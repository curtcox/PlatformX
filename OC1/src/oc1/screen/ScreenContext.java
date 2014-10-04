package oc1.screen;

import java.util.HashMap;
import java.util.Map;
import oc1.log.Log;
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
            String message = "No value found for " + key + " in " + values;
            log(new IllegalArgumentException(message));
            log(message);
        }
        return (value instanceof Getter) ? ((Getter)value).get() : value;
    }
    
    private void log(Exception e) {
        getLog().log(e);    
    }

    private void log(String message) {
        getLog().log(message);    
    }

    private Log getLog() {
        return LogManager.of().getLog(ScreenContext.class);    
    }

}
