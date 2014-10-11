package hash;

import java.util.Map;

/**
 *
 * @author Curt
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
