package c1.device;

import java.util.*;

/**
 * For building a plain-text report.
 * @author Curt
 */
final class ReportBuilder {

    final List<DeviceKeyValuePair> list = new ArrayList<DeviceKeyValuePair>();
    final StringBuilder out = new StringBuilder();

    void value(String key, Object value) {
        list.add(new DeviceKeyValuePair(key,value));
        out.append("\t" + key + "=" + value);
        out.append("\r\n");
    }

    void section(String string, List<DeviceKeyValuePair> pairs) {
        out.append(string);
        out.append("\r\n");
        for (DeviceKeyValuePair pair : pairs) {
            value(pair.key,pair.value);
        }
    }

    @Override
    public String toString() {
        return out.toString();
    }

    List<DeviceKeyValuePair> toKeyValuePairs() {
        return list;
    }
}
