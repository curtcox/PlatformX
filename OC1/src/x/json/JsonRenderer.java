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

    public static Json object(Object o) {
        if (o instanceof Json) {
            return (Json) o;
        }
        if (o instanceof List) {
            return list((List)o);
        }
        if (o instanceof Map) {
            return map((Map)o);
        }
        return JsonValue.of(o);
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
