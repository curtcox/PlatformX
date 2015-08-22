package ios;

import org.robovm.apple.uikit.UIView;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;

public final class IosUtil {

    private IosUtil() {}

    public static void dumpView(UIView view) {
        log("Starting dump of " + view);
        printView(view, "");
        log("Finished dump of " + view);
    }

    private static void printView(UIView view, String prefix) {
        print(view,prefix);
        for (UIView child : view.getSubviews()) {
            printView(child, prefix + " | ");
        }
    }

    private static void print(UIView view, String prefix) {
        log(prefix + view);
    }

    private static void log(String message) {
        getLog().log(message);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IosUtil.class);
    }

}
