package x.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class JsonRenderer {

    public static JsonList list(Object... args) {
        List list = new ArrayList();
        for (Object arg : args) {
            list.add(JsonRenderer.object(arg));
        }
        return JsonList.of(list);
    }

    private static Json object(Object arg) {
        return null;
    }

    public static JsonMap map(Map map) {
        return null;
    }
}
