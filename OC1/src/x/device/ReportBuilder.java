package x.device;

import x.event.LiveList;
import x.event.XLiveList;
import x.pagefactories.NamedValue;

import java.util.ArrayList;
import java.util.List;

/**
 * For building a plain-text report.
 */
public final class ReportBuilder {

    final List<NamedValue> list = new ArrayList<NamedValue>();
    final StringBuilder out = new StringBuilder();

    public void value(String key, Object value) {
        list.add(new NamedValue(key,value));
        out.append("\t" + key + "=" + value);
        out.append("\r\n");
    }

    public void section(String string, List<NamedValue> pairs) {
        out.append(string);
        out.append("\r\n");
        for (NamedValue pair : pairs) {
            value(pair.name,pair.value);
        }
    }

    @Override
    public String toString() {
        return out.toString();
    }

    public LiveList<NamedValue> toKeyValuePairs() {
        return XLiveList.of(list);
    }
}
