package common.util;

import java.util.HashMap;
import java.util.Map;

public final class SimpleMutableStringMap
    implements MutableStringMap
{
    Map map = new HashMap();

    @Override
    public void put(String key, String value) {
        map.put(key,value);
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public String get(String key) {
        return (String) map.get(key);
    }
}
