package se.device;

import common.device.DeviceKeyValuePair;
import common.device.IDeviceInfo;
import common.device.ReportBuilder;

import java.util.List;

/**
 * For generating a dump of device-specific info.
 */
public final class SEDeviceInfo
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
        return out;
    }

    private static List<DeviceKeyValuePair> runtimeInfo() {
        Runtime runtime = Runtime.getRuntime();
        ReportBuilder out = new ReportBuilder();
        out.value("free memory"  , runtime.freeMemory());
        out.value("total memory" , runtime.totalMemory());
        return out.toKeyValuePairs();
    }

}
