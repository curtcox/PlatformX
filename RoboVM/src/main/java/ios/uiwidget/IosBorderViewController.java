package ios.uiwidget;

import org.robovm.apple.uikit.UIViewController;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;

public final class IosBorderViewController
    extends UIViewController
{
    UIViewController center;
    UIViewController north;
    UIViewController west;
    UIViewController east;
    IosBorderContainer borderView;

    public static IosBorderViewController of(UIViewController center) {
        IosBorderViewController controller = new IosBorderViewController();
        controller.center(center);
        return controller;
    }

    private IosBorderViewController center(UIViewController center) {
        this.center = center;
        addChildViewController(center);
        borderView = IosBorderContainer.of(center.getView());
        setView(borderView);
        return this;
    }

    public IosBorderViewController north(UIViewController north) {
        this.north = north;
        addChildViewController(north);
        borderView.north(north.getView());
        return this;
    }

    public IosBorderViewController west(UIViewController west) {
        this.west = west;
        addChildViewController(west);
        borderView.west(west.getView());
        return this;
    }

    public IosBorderViewController east(UIViewController east) {
        this.east = east;
        addChildViewController(east);
        borderView.east(east.getView());
        return this;
    }

    @Override
    public void viewWillLayoutSubviews() {
        log("viewWillLayoutSubviews" + this + getView());
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IosBorderViewController.class);
    }
}
