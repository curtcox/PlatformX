package oc1.log;

import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.LookAndFeel;
import com.codename1.ui.plaf.UIManager;

/**
 *
 * @author Curt
 */
final class DeviceInfo {

    static String dump() {
        ReportBuilder out = new ReportBuilder();
        out.section(displayInfo());
        out.section(runtimeInfo());
        out.section(networkInfo());
        out.section(UIManagerInfo());
        out.section(LookAndFeelInfo());
        out.section(AndroidProperties());
        return out.toString();
    }

    static private String displayInfo() {
        Display display = Display.getInstance();
        ReportBuilder out = new ReportBuilder();
        out.heading("Display");
        out.add("Get");
        out.add("platform name="           + display.getPlatformName());
        out.add("MSISDN="                  + display.getMsisdn());
        out.add("UDID="                    + display.getUdid());
        out.add("device density="          + display.getDeviceDensity());
        out.add("display height="          + display.getDisplayHeight());
        out.add("display width="           + display.getDisplayWidth());
        out.add("frame rate="              + display.getFrameRate());
        out.add("Are?");
        out.add("mutable images fast=" + display.areMutableImagesFast());
        out.add("Can?");
        out.add("force orientation="   + display.canForceOrientation());
        out.add("dial="                + display.canDial());
        out.add("Is?");
        out.add("AllowMinimizing="             + display.isAllowMinimizing());
        out.add("AutoFoldVKBOnFormSwitch="     + display.isAutoFoldVKBOnFormSwitch());
        out.add("BadgingSupported="            + display.isBadgingSupported());
        out.add("BidiAlgorithm="               + display.isBidiAlgorithm());
        out.add("BuiltinSoundsEnabled="        + display.isBuiltinSoundsEnabled());
        out.add("ClickTouchScreen="            + display.isClickTouchScreen());
        out.add("ContactsPermissionGranted="   + display.isContactsPermissionGranted());
        out.add("Desktop="                     + display.isDesktop());
        out.add("Edt="                         + display.isEdt());
        out.add("Minimized="                   + display.isMinimized());
        out.add("MultiKeyMode="                + display.isMultiKeyMode());
        out.add("MultiTouch="                  + display.isMultiTouch());
        out.add("NativeInputSupported="        + display.isNativeInputSupported());
        out.add("NativeShareSupported="        + display.isNativeShareSupported());
        out.add("NativeTitle="                 + display.isNativeTitle());
        out.add("NativeVideoPlayerControlsIncluded=" + display.isNativeVideoPlayerControlsIncluded());
        out.add("NotificationSupported="             + display.isNotificationSupported());
        out.add("OpenNativeNavigationAppSupported="  + display.isOpenNativeNavigationAppSupported());
        out.add("Portrait="                    + display.isPortrait());
        out.add("PureTouch="                   + display.isPureTouch());
        out.add("ScreenSaverDisableSupported=" + display.isScreenSaverDisableSupported());
        out.add("Tablet="                      + display.isTablet());
        out.add("TouchScreenDevice="           + display.isTouchScreenDevice());
        out.add("VirtualKeyboardShowing="      + display.isVirtualKeyboardShowing());
        Form form = display.getCurrent();
        if (form==null) {
            out.add("form=null");
         } else {
            out.add("form=" + form.getName());
        }
        return out.toString();
    }

    static private String networkInfo() {
        NetworkManager network = NetworkManager.getInstance();
        ReportBuilder out = new ReportBuilder();
        out.heading("Network");
        out.add("ThreadCount="        + network.getThreadCount());
        out.add("Timeout="            + network.getTimeout());
        out.add("APSupported="        + network.isAPSupported());
        out.add("QueueIdle="          + network.isQueueIdle());
        return out.toString();
    }

    private static String runtimeInfo() {
        Runtime runtime = Runtime.getRuntime();
        ReportBuilder out = new ReportBuilder();
        out.heading("Runtime");
        out.add("free memory="  + runtime.freeMemory());
        out.add("total memory=" + runtime.totalMemory());
        return out.toString();
    }

    private static String UIManagerInfo() {
        UIManager manager = UIManager.getInstance();
        ReportBuilder out = new ReportBuilder();
        out.heading("UIManager");
        out.add("ThemeName="  + manager.getThemeName());
        return out.toString();
     }

    private static String LookAndFeelInfo() {
        LookAndFeel laf = UIManager.getInstance().getLookAndFeel();
        ReportBuilder out = new ReportBuilder();
        out.heading("LookAndFeel");
        out.add("isBackgroundImageDetermineSize="  + laf.isBackgroundImageDetermineSize());
        out.add("isDefaultAlwaysTensile="          + laf.isDefaultAlwaysTensile());
        out.add("isDefaultEndsWith3Points="        + laf.isDefaultEndsWith3Points());
        out.add("isDefaultSmoothScrolling="        + laf.isDefaultSmoothScrolling());
        out.add("isDefaultSnapToGrid="             + laf.isDefaultSnapToGrid());
        out.add("isDefaultTensileDrag="            + laf.isDefaultTensileDrag());
        out.add("isDefaultTensileHighlight="       + laf.isDefaultTensileHighlight());
        out.add("isFadeScrollBar="                 + laf.isFadeScrollBar());
        out.add("isFadeScrollEdge="                + laf.isFadeScrollEdge());
        out.add("isFocusScrolling="                + laf.isFocusScrolling());
        out.add("isRTL="                           + laf.isRTL());
        out.add("isReverseSoftButtons="            + laf.isReverseSoftButtons());
        return out.toString();
    }

    private static String AndroidProperties() {
        ReportBuilder out = new ReportBuilder();
        out.heading("Android Properties");
        for (String key : androidPropertyKeys()) {
            String value = System.getProperty(key);
            if (value!=null) {
                out.add(key + "=" + value);
            }
        }
        return out.toString();
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
            "ro.product.name",
            "ro.product.device",
            "ro.product.brand",
            "ro.product.board",
            "ro.product.model",
            "ro.build.display.id",
            "ro.build.version.incremental",
            "ro.build.version.release",
            "ro.build.type",
            "ro.build.tags",
            "ro.build.fingerprint",
            "ro.build.version.sdk",
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
