package ios.app;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.*;

public class IosApplication extends UIApplicationDelegateAdapter {
    private UIWindow window;
    private MyViewController rootViewController;

    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        // Set up the view controller.
        rootViewController = new MyViewController();

        // Create a new window at screen size.
        window = new UIWindow(UIScreen.getMainScreen().getBounds());
        // Set the view controller as the root controller for the window.
        window.setRootViewController(rootViewController);
        // Make the window visible.
        window.makeKeyAndVisible();

        return true;
    }

    public static void main(String[] args) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        try {
            UIApplication.main(args, null, IosApplication.class);
        } finally {
            pool.close();
        }
    }
}
