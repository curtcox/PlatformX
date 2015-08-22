package ios.ui;

import ios.IosUtil;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIWindow;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.ui.IDisplay;
import x.ui.IForm;

public final class IosDisplay
    implements IDisplay
{
    private IosForm form;
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
        showFormInWindow(form, newWindow());
        dumpForm();
    }

    private void dumpForm() {
        IosUtil.dumpController(form);
        IosUtil.dumpView(form.getView());
    }

    private void showFormInWindow(IosForm form, UIWindow window) {
        log("show " + form + " in " + window);
        window.setRootViewController(form);
        window.makeKeyAndVisible();
    }

    private UIWindow newWindow() {
        return new UIWindow(frame());
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
        return Registry.get(ILogManager.class).getLog(IosDisplay.class);
    }
}
