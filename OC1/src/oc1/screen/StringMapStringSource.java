package oc1.screen;

import oc1.event.StringSource;
import oc1.util.StringMap;

/**
 *
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
