package oc1.screens;

import com.codename1.ui.Display;

/**
 *
 * @author Curt
 */
final class DeviceInfo {

    static String dump() {
        StringBuilder out = new StringBuilder();
        addDisplay(out);
        addRuntime(out);
        addAndroidProperties(out);
        return out.toString();
    }

    private static void add(StringBuilder out, String string) {
        out.append(string);
        out.append("\r\n");
    }

    private static void addDisplay(StringBuilder out) {
        Display display = Display.getInstance();
        add(out,"Display");
        add(out,"platform name="           + display.getPlatformName());
        add(out,"MSISDN="                  + display.getMsisdn());
        add(out,"UDID="                    + display.getUdid());
        add(out,"can force orientation="   + display.canForceOrientation());
        add(out,"device density="          + display.getDeviceDensity());
        add(out,"display height="          + display.getDisplayHeight());
        add(out,"display width="           + display.getDisplayWidth());
        add(out,"frame rate="               + display.getFrameRate());
        add(out,"are mutable images fast=" + display.areMutableImagesFast());
        add(out,"can dial="                + display.canDial());
    }

    private static void addRuntime(StringBuilder out) {
        Runtime runtime = Runtime.getRuntime();
        add(out,"Runtime");
        add(out,"free memory="  + runtime.freeMemory());
        add(out,"total memory=" + runtime.totalMemory());
    }

    private static void addAndroidProperties(StringBuilder out) {
        add(out,"Android Properties");
        for (String key : androidPropertyKeys()) {
            String value = System.getProperty(key);
            if (value!=null) {
                add(out,key + "=" + value);
            }
        }
    }

    private static String[] androidPropertyKeys() {
        return new String[] {
            "android.icu.library.version",
            "android.icu.unicode.version",
            "android.openssl.version",
            "android.zlib.version",
            "file.encoding",
            "file.separator",
            "http.agent",
            "java.boot.class.path",
            "java.class.path",
            "java.class.version",
            "java.compiler",
            "java.ext.dirs",
            "java.home",
            "java.io.tmpdir",
            "java.library.path",
            "java.runtime.name",
            "java.runtime.version",
            "java.specification.name",
            "java.specification.vendor",
            "java.specification.version",
            "java.vendor",
            "java.vendor.url",
            "java.version",
            "java.vm.name",
            "java.vm.specification.name",
            "java.vm.specification.vendor",
            "java.vm.specification.version",
            "java.vm.vendor",
            "java.vm.vendor.url",
            "java.vm.version",
            "line.separator",
            "os.arch",
            "os.name",
            "os.version",
            "path.separator",
            "user.dir",
            "user.home",
            "user.language",
            "user.name",
            "user.region",
        };
    }
}
