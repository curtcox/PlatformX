package ios.ui;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIWindow;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.ui.IDisplay;
import x.ui.IForm;

public final class IosDisplay
    implements IDisplay
{
    private IosForm form;
    private UIWindow window;
    private static IosDisplay singleton;

    private IosDisplay() {}

    public static IosDisplay of() {
        if (singleton==null) {
            singleton = newDisplay();
        }
        return singleton;
    }

    private static IosDisplay newDisplay() {
        return new IosDisplay();
    }

    @Override
    public boolean isPortrait() {
        return true;
    }

    @Override
    public IForm getCurrent() {
        return form;
    }

    void show(IosForm form) {
        this.form = form;
        showFormInWindow(form, window());
    }

    private void showFormInWindow(IosForm form, UIWindow window) {
        log("show " + form + " in " + window);
        window.setRootViewController(form);
        window.makeKeyAndVisible();
    }

    private UIWindow window() {
        if (window!=null) {
            return window;
        }
        window = new UIWindow(frame());
        return window;
    }

    private CGRect frame() {
        return UIScreen.getMainScreen().getBounds();
    }

    @Override
    public void execute(String url) {
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(this);
    }
}
