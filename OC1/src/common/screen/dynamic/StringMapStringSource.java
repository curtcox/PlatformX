package common.screen.dynamic;

import common.event.StringSource;
import common.util.StringMap;

/**
 * For using a StringMap as a StringSource
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