package common.util;

import java.util.Map;

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
