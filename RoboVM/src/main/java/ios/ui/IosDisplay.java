package ios.ui;

import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIWindow;
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
        UIWindow window = newWindow();
        window.setRootViewController(form);
        window.makeKeyAndVisible();
    }

    private UIWindow newWindow() {
        return new UIWindow(UIScreen.getMainScreen().getBounds());
    }

    @Override
    public void execute(String url) {
    }
}
