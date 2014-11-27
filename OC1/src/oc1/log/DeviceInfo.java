package oc1.log;

import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.LookAndFeel;
import com.codename1.ui.plaf.UIManager;

/**
 * For generating a dump of device-specific info.
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
        out.note("Get");
        out.value("platform name"           , display.getPlatformName());
        out.value("MSISDN"                  , display.getMsisdn());
        out.value("UDID"                    , display.getUdid());
        out.value("device density"          , display.getDeviceDensity());
        out.value("display height"          , display.getDisplayHeight());
        out.value("display width"           , display.getDisplayWidth());
        out.value("frame rate"              , display.getFrameRate());
        out.note("Are?");
        out.value("mutable images fast" , display.areMutableImagesFast());
        out.note("Can?");
        out.value("force orientation"   , display.canForceOrientation());
        out.value("dial"                , display.canDial());
        out.note("Is?");
        out.value("AllowMinimizing"             , display.isAllowMinimizing());
        out.value("AutoFoldVKBOnFormSwitch"     , display.isAutoFoldVKBOnFormSwitch());
        out.value("BadgingSupported"            , display.isBadgingSupported());
        out.value("BidiAlgorithm"               , display.isBidiAlgorithm());
        out.value("BuiltinSoundsEnabled"        , display.isBuiltinSoundsEnabled());
        out.value("ClickTouchScreen"            , display.isClickTouchScreen());
        out.value("ContactsPermissionGranted"   , display.isContactsPermissionGranted());
        out.value("Desktop"                     , display.isDesktop());
        out.value("Edt"                         , display.isEdt());
        out.value("Minimized"                   , display.isMinimized());
        out.value("MultiKeyMode"                , display.isMultiKeyMode());
        out.value("MultiTouch"                  , display.isMultiTouch());
        out.value("NativeInputSupported"        , display.isNativeInputSupported());
        out.value("NativeShareSupported"        , display.isNativeShareSupported());
        out.value("NativeTitle"                 , display.isNativeTitle());
        out.value("NativeVideoPlayerControlsIncluded" , display.isNativeVideoPlayerControlsIncluded());
        out.value("NotificationSupported"             , display.isNotificationSupported());
        out.value("OpenNativeNavigationAppSupported"  , display.isOpenNativeNavigationAppSupported());
        out.value("Portrait"                    , display.isPortrait());
        out.value("PureTouch"                   , display.isPureTouch());
        out.value("ScreenSaverDisableSupported" , display.isScreenSaverDisableSupported());
        out.value("Tablet"                      , display.isTablet());
        out.value("TouchScreenDevice"           , display.isTouchScreenDevice());
        out.value("VirtualKeyboardShowing"      , display.isVirtualKeyboardShowing());
        Form form = display.getCurrent();
        if (form==null) {
            out.value("form","null");
         } else {
            out.value("form" , form.getName());
        }
        return out.toString();
    }

    static private String networkInfo() {
        NetworkManager network = NetworkManager.getInstance();
        ReportBuilder out = new ReportBuilder();
        out.heading("Network");
        out.value("ThreadCount"        , network.getThreadCount());
        out.value("Timeout"            , network.getTimeout());
        out.value("APSupported"        , network.isAPSupported());
        out.value("QueueIdle"          , network.isQueueIdle());
        return out.toString();
    }

    private static String runtimeInfo() {
        Runtime runtime = Runtime.getRuntime();
        ReportBuilder out = new ReportBuilder();
        out.heading("Runtime");
        out.value("free memory"  , runtime.freeMemory());
        out.value("total memory" , runtime.totalMemory());
        return out.toString();
    }

    private static String UIManagerInfo() {
        UIManager manager = UIManager.getInstance();
        ReportBuilder out = new ReportBuilder();
        out.heading("UIManager");
        out.value("ThemeName="  , manager.getThemeName());
        return out.toString();
     }

    private static String LookAndFeelInfo() {
        LookAndFeel laf = UIManager.getInstance().getLookAndFeel();
        ReportBuilder out = new ReportBuilder();
        out.heading("LookAndFeel");
        out.value("isBackgroundImageDetermineSize"  , laf.isBackgroundImageDetermineSize());
        out.value("isDefaultAlwaysTensile"          , laf.isDefaultAlwaysTensile());
        out.value("isDefaultEndsWith3Points"        , laf.isDefaultEndsWith3Points());
        out.value("isDefaultSmoothScrolling"        , laf.isDefaultSmoothScrolling());
        out.value("isDefaultSnapToGrid"             , laf.isDefaultSnapToGrid());
        out.value("isDefaultTensileDrag"            , laf.isDefaultTensileDrag());
        out.value("isDefaultTensileHighlight"       , laf.isDefaultTensileHighlight());
        out.value("isFadeScrollBar"                 , laf.isFadeScrollBar());
        out.value("isFadeScrollEdge"                , laf.isFadeScrollEdge());
        out.value("isFocusScrolling"                , laf.isFocusScrolling());
        out.value("isRTL"                           , laf.isRTL());
        out.value("isReverseSoftButtons"            , laf.isReverseSoftButtons());
        return out.toString();
    }

    private static String AndroidProperties() {
        ReportBuilder out = new ReportBuilder();
        out.heading("Android Properties");
        for (String key : androidPropertyKeys()) {
            String value = System.getProperty(key);
            if (value!=null) {
                out.value(key + "=" , value);
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
