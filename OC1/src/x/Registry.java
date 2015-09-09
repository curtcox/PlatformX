package x;

import x.event.LiveList;
import x.event.XLiveList;
import x.pagefactories.KeyValuePair;
import x.pagefactories.KeyValuePairListSource;

import java.util.*;

/**
 * Global parts bin.
 * The Registry acts as a layer of indirection, so that clients can use things
 * without needing to know how to construct them or their exact implementation. 
 */
public final class Registry
    implements KeyValuePairListSource
{

    private static final Map<Class,Object> values = new HashMap<Class,Object>();
    static {
        put(Registry.class,new Registry());
    }

    public static <T> T get(Class<T> clazz) {
        if (Registry.values.containsKey(clazz)) {
            return (T) Registry.values.get(clazz);
        }
        throw new RuntimeException("No registered " + clazz.getSimpleName());
    }

    public static <T> void put(Class<T> key, T value) {
        Registry.values.put(key, value);
    }

    @Override
    public LiveList<KeyValuePair> asKeyValuePairs() {
        Map map = new HashMap(values);
        List list = new ArrayList();
        for (Object key : map.keySet()) {
            String keyString = "" + key;
            Object value = map.get(key);
            list.add(new KeyValuePair(keyString,value));
        }
        return new XLiveList(list);
    }
}
