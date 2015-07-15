package hash;

import java.util.Map;

/**
 * A map from names to expressions.
 */
final class SimpleNamedValues
    implements NamedValues
{

    final Map<String, Expression> map;
    
    public SimpleNamedValues(Map<String, Expression> map) {
        this.map = map;
    }

    public Expression get(String name) {
        return map.get(name);
    }
    
    @Override
    public String toString() {
        return map.toString();
    }
}
