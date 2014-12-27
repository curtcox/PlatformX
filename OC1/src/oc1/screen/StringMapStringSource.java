package oc1.screen;

import common.event.StringSource;
import oc1.util.StringMap;

/**
 * For using a StringMap as a StringSource
 * @author Curt
 */
public final class StringMapStringSource
    implements StringSource
{
    private final StringMap map;
    private final String key;
    
    public StringMapStringSource(StringMap map, String key) {
        this.map = map;
        this.key = key;
    }
    
    public String getString() {
        return map.get(key);
    }
    
}
