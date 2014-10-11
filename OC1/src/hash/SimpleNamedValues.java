package hash;

import java.util.Map;

/**
 *
 * @author Curt
 */
final class SimpleNamedValues<V>
    implements NamedValues<V>
{

    final Map<String, V> map;
    
    public SimpleNamedValues(Map<String, V> map) {
        this.map = map;
    }

    public V get(String name) {
        return map.get(name);
    }
    
    @Override
    public String toString() {
        return map.toString();
    }
}
