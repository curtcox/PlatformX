package ios;

import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;

public final class IosUtil {

    private IosUtil() {}

    public static void dumpViewHierarchy(UIView view) {
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

    public static void dumpControllerHierarchy(UIViewController controller) {
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

    public static void viewInfo(UIView view) {
        log("description=" + view.description());
        log("isUserInteractionEnabled=" + view.isUserInteractionEnabled());
        log("frame=" + view.getFrame());
        log("bounds=" + view.getBounds());
        log("center=" + view.getCenter());
        log("transform=" + view.getTransform());
        log("contentScaleFactor=" + view.getContentScaleFactor());
        log("isMultipleTouchEnabled=" + view.isMultipleTouchEnabled());
        log("isExclusiveTouch=" + view.isExclusiveTouch());
        log("autoresizes Subviews=" + view.autoresizesSubviews());
        log("superview=" + view.getSuperview());
        log("subviews=" + view.getSubviews());
        log("window=" + view.getWindow());
        log("layoutMargins=" + view.getLayoutMargins());
        log("preservesSuperviewLayoutMargins=" + view.preservesSuperviewLayoutMargins());
        log("clipsToBounds=" + view.clipsToBounds());
        log("backgroundColor=" + view.getBackgroundColor());
        log("alpha=" + view.getAlpha());
        log("opaque=" + view.isOpaque());
        log("clearsContextBeforeDrawing=" + view.clearsContextBeforeDrawing());
        log("Hidden=" + view.isHidden());
        log("contentMode=" + view.getContentMode());
        log("contentStretch=" + view.getContentStretch());
        log("maskView=" + view.getMaskView());
        log("tintColor=" + view.getTintColor());
        log("tintAdjustmentMode=" + view.getTintAdjustmentMode());
        log("gestureRecognizers=" + view.getGestureRecognizers());
        log("restorationIdentifier=" + view.getRestorationIdentifier());
        log("traitCollection=" + view.getTraitCollection());
        log("accessibilityIdentifier=" + view.getAccessibilityIdentifier());
        log("description=" + view.description());
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
