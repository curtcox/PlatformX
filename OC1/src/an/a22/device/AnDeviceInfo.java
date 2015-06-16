package an.a22.device;

import common.device.DeviceKeyValuePair;
import common.device.IDeviceInfo;
import common.device.ReportBuilder;

import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

/**
 * For generating a dump of device-specific info.
 */
public final class AnDeviceInfo
    implements IDeviceInfo
{

    public List asDeviceKeyValuePairs() {
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

    private static List<DeviceKeyValuePair> runtimeInfo() {
        Runtime runtime = Runtime.getRuntime();
        ReportBuilder out = new ReportBuilder();
        out.value("free memory", runtime.freeMemory());
        out.value("total memory" , runtime.totalMemory());
        out.value("max memory", runtime.maxMemory());
        return out.toKeyValuePairs();
    }

    private static List<DeviceKeyValuePair> systemInfo() {
        Properties properties = System.getProperties();
        ReportBuilder out = new ReportBuilder();
        for (Object key : new TreeSet(properties.keySet())) {
            out.value(key.toString(), properties.get(key));
        }
        return out.toKeyValuePairs();
    }

}