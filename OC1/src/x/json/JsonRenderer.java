package x.json;

import java.util.*;

public final class JsonRenderer {

    public static JsonList list(Object... args) {
        return list(Arrays.asList(args));
    }

    public static JsonList list(List list) {
        List<Json> json = new ArrayList();
        for (Object arg : list) {
            json.add(object(arg));
        }
        return JsonList.of(json);
    }

    public static Json object(Object arg) {
        if (arg instanceof Json) {
            return (Json) arg;
        }
        return JsonValue.of(arg);
    }

    public static JsonMap map(Map map) {
        Map<String,Json> json = new HashMap();
        for (Object key : map.keySet()) {
            Object value = map.get(key);
            json.put((String) key,object(value));
        }
        return JsonMap.of(json);
    }
}
