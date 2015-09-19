package x.pagefactories;

import x.event.LiveList;
import x.event.NamedValueListSource;
import x.event.XLiveList;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

final class ObjectToLiveList {

    static LiveList from(Object object) {
        if (object instanceof NamedValueListSource) {
            return ((NamedValueListSource) object).asNamedValues();
        }
        if (object instanceof LiveList.Source) {
            return ((LiveList.Source) object).asLiveList();
        }
        if (object instanceof List) {
            return XLiveList.of((List) object);
        }
        if (object instanceof Collection) {
            return XLiveList.of((Collection) object);
        }
        if (object instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) object;
            return XLiveList.of(Arrays.asList(entry.getKey(),entry.getValue()));
        }
        return XLiveList.of(Arrays.asList(object));
    }

}
