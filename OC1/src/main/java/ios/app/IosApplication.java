package ios.app;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.*;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.page.PageLink;
import x.screen.Screen;

public final class IosApplication
        extends UIApplicationDelegateAdapter
{
    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        try {
            IosRegistryLoader.load();
        } catch(Exception e){
            log(e);
        }
        show();
        log("onCreate(" + application + "," +  launchOptions + ") finished");

        return true;
    }

    private void show() {
        Screen.show(PageLink.of(""));
    }

    private void log(Object context) {
        getLog().log("init("+context +")");
    }

    private void log(Exception e) {
        getLog().log(e);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(this);
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
