package oc1.log;

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
        StringBuilder out = new StringBuilder();
        addDisplay(out);
        addRuntime(out);
        addUIManager(out);
        addLookAndFeel(out);
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
        add(out," get");
        add(out,"platform name="           + display.getPlatformName());
        add(out,"MSISDN="                  + display.getMsisdn());
        add(out,"UDID="                    + display.getUdid());
        add(out,"device density="          + display.getDeviceDensity());
        add(out,"display height="          + display.getDisplayHeight());
        add(out,"display width="           + display.getDisplayWidth());
        add(out,"frame rate="              + display.getFrameRate());
        add(out," are");
        add(out,"are mutable images fast=" + display.areMutableImagesFast());
        add(out," can");
        add(out,"can force orientation="   + display.canForceOrientation());
        add(out,"can dial="                + display.canDial());
        add(out," is");
        add(out," isAllowMinimizing="             + display.isAllowMinimizing());
        add(out," isAutoFoldVKBOnFormSwitch="     + display.isAutoFoldVKBOnFormSwitch());
        add(out," isBadgingSupported="            + display.isBadgingSupported());
        add(out," isBidiAlgorithm="               + display.isBidiAlgorithm());
        add(out," isBuiltinSoundsEnabled="        + display.isBuiltinSoundsEnabled());
        add(out," isClickTouchScreen="            + display.isClickTouchScreen());
        add(out," isContactsPermissionGranted="   + display.isContactsPermissionGranted());
        add(out," isDesktop="                     + display.isDesktop());
        add(out," isEdt="                         + display.isEdt());
        add(out," isMinimized="                   + display.isMinimized());
        add(out," isMultiKeyMode="                + display.isMultiKeyMode());
        add(out," isMultiTouch="                  + display.isMultiTouch());
        add(out," isNativeInputSupported="        + display.isNativeInputSupported());
        add(out," isNativeShareSupported="        + display.isNativeShareSupported());
        add(out," isNativeTitle="                 + display.isNativeTitle());
        add(out," isNativeVideoPlayerControlsIncluded=" + display.isNativeVideoPlayerControlsIncluded());
        add(out," isNotificationSupported="       + display.isNotificationSupported());
        add(out," isOpenNativeNavigationAppSupported=" + display.isOpenNativeNavigationAppSupported());
        add(out," isPortrait="                    + display.isPortrait());
        add(out," isPureTouch="                   + display.isPureTouch());
        add(out," isScreenSaverDisableSupported=" + display.isScreenSaverDisableSupported());
        add(out," isTablet="                      + display.isTablet());
        add(out," isTouchScreenDevice="           + display.isTouchScreenDevice());
        add(out," isVirtualKeyboardShowing="      + display.isVirtualKeyboardShowing());
        Form form = display.getCurrent();
        if (form==null) {
            add(out,"form=null");
         } else {
            add(out,"form=" + form.getName());
        }
    }

    private static void addRuntime(StringBuilder out) {
        Runtime runtime = Runtime.getRuntime();
        add(out,"Runtime");
        add(out,"free memory="  + runtime.freeMemory());
        add(out,"total memory=" + runtime.totalMemory());
    }

    private static void addUIManager(StringBuilder out) {
        UIManager manager = UIManager.getInstance();
        add(out,"UIManager");
        add(out,"ThemeName="  + manager.getThemeName());
    }

    private static void addLookAndFeel(StringBuilder out) {
        LookAndFeel laf = UIManager.getInstance().getLookAndFeel();
        add(out,"LookAndFeel");
        add(out,"isBackgroundImageDetermineSize="  + laf.isBackgroundImageDetermineSize());
        add(out,"isDefaultAlwaysTensile="          + laf.isDefaultAlwaysTensile());
        add(out,"isDefaultEndsWith3Points="        + laf.isDefaultEndsWith3Points());
        add(out,"isDefaultSmoothScrolling="        + laf.isDefaultSmoothScrolling());
        add(out,"isDefaultSnapToGrid="             + laf.isDefaultSnapToGrid());
        add(out,"isDefaultTensileDrag="            + laf.isDefaultTensileDrag());
        add(out,"isDefaultTensileHighlight="       + laf.isDefaultTensileHighlight());
        add(out,"isFadeScrollBar="                 + laf.isFadeScrollBar());
        add(out,"isFadeScrollEdge="                + laf.isFadeScrollEdge());
        add(out,"isFocusScrolling="                + laf.isFocusScrolling());
        add(out,"isRTL="                           + laf.isRTL());
        add(out,"isReverseSoftButtons="            + laf.isReverseSoftButtons());
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
