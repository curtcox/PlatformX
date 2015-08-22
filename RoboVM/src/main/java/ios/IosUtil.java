package ios;

import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;
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

    public static void dumpController(UIViewController controller) {
        log("Starting dump of " + controller);
        printView(controller, "");
        log("Finished dump of " + controller);
    }

    private static void printView(UIViewController controller, String prefix) {
        print(controller,prefix);
        for (UIViewController child : controller.getChildViewControllers()) {
            printView(child, prefix + " | ");
        }
    }

    private static void print(UIViewController view, String prefix) {
        log(prefix + view);
    }

    public static void viewControllerInfo(UIViewController controller) {
        log("description=" + controller.description());
        log("title=" + controller.getTitle());
        log("first responder=" + controller.isFirstResponder());
        log("view=" + controller.getView());
        log("editing=" + controller.isEditing());
        log("definesPresentationContext=" + controller.definesPresentationContext());
        log("wantsFullScreenLayout=" + controller.wantsFullScreenLayout());
        log("automaticallyAdjustsScrollViewInsets=" + controller.automaticallyAdjustsScrollViewInsets());
        log("isModalInPopover=" + controller.isModalInPopover());
        log("isBeingPresented=" + controller.isBeingPresented());
        log("isBeingDismissed=" + controller.isBeingDismissed());
        log("isMovingToParentViewController=" + controller.isMovingToParentViewController());
        log("isMovingFromParentViewController=" + controller.isMovingFromParentViewController());
        log("disablesAutomaticKeyboardDismissal=" + controller.disablesAutomaticKeyboardDismissal());
        log("prefersStatusBarHidden=" + controller.prefersStatusBarHidden());
        log("shouldAutorotate=" + controller.shouldAutorotate());
        log("shouldAutomaticallyForwardRotationMethods=" + controller.shouldAutomaticallyForwardRotationMethods());
        log("shouldAutomaticallyForwardAppearanceMethods=" + controller.shouldAutomaticallyForwardAppearanceMethods());
    }

    private static void log(String message) {
        getLog().log(message);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IosUtil.class);
    }

}
