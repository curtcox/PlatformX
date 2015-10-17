package x.json;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public final class JsonMap
    implements Json, Map<String,Json>
{

    private final Map<String,Json> map;

    private JsonMap(Map<String,Json> map) {
        this.map = map;
    }

    static JsonMap of(Map<String,Json> map) {
        return new JsonMap(map);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Json get(Object key) {
        return map.get(key);
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Json> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String,Json>> entrySet() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        return map.toString();
    }
    // ------------- Never --------------
    private UnsupportedOperationException never() {
        return new UnsupportedOperationException();
    }

    @Override
    public Json put(String  key, Json value) {
        throw never();
    }

    @Override
    public Json remove(Object key) {
        throw never();
    }

    @Override
    public void putAll(Map m) {
        throw never();
    }

    @Override
    public void clear() {
        throw never();
    }

}
