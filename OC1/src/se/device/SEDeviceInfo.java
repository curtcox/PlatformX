package se.device;

import x.device.ReportBuilder;
import x.device.XDeviceInfo;
import x.event.LiveList;
import x.pagefactories.NamedValue;

import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

/**
 * For generating a dump of device-specific info.
 */
public final class SEDeviceInfo
    implements XDeviceInfo
{

    public LiveList asNamedValues() {
        return buildReport().toKeyValuePairs();
    }

    public static String dump() {
        return buildReport().toString();
    }

    static ReportBuilder buildReport() {
        ReportBuilder out = new ReportBuilder();
        out.section("Runtime",runtimeInfo());
        out.section("System",systemInfo());
        return out;
    }

    private static List<NamedValue> runtimeInfo() {
        Runtime runtime = Runtime.getRuntime();
        ReportBuilder out = new ReportBuilder();
        out.value("free memory", runtime.freeMemory());
        out.value("total memory" , runtime.totalMemory());
        out.value("max memory", runtime.maxMemory());
        return out.toKeyValuePairs();
    }

    private static List<NamedValue> systemInfo() {
        Properties properties = System.getProperties();
        ReportBuilder out = new ReportBuilder();
        for (Object key : new TreeSet(properties.keySet())) {
            out.value(key.toString(), properties.get(key));
        }
        return out.toKeyValuePairs();
    }

}
