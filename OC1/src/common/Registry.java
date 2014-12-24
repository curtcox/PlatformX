package common;

import java.util.HashMap;
import java.util.Map;

/**
 * Global parts bin.
 * The Registry acts as a layer of indirection, so that clients can use things
 * without needing to know how to construct them or their exact implementation. 
 * @author Curt
 */
public final class Registry {

    private static final Map<Class,Object> values = new HashMap<Class,Object>();
    
    public static <T> T get(Class<T> clazz) {
        if (Registry.values.containsKey(clazz)) {
            return (T) Registry.values.get(clazz);
        }
        throw new RuntimeException("No registered " + clazz.getSimpleName());
    }

    public static <T> void put(Class<T> key, T value) {
        Registry.values.put(key, value);
    }
    
}
