package oc1.util;

import java.util.Map;

/**
 *
 * @author Curt
 */
public final class SimpleStringMap
    implements StringMap
{
    private final Map<String,String> map;

    public SimpleStringMap(Map<String,String> map) {
        this.map = map;
    }
    
    public String get(String key) {
        return map.get(key);
    }
    
}
