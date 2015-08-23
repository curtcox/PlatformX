package ios.uiwidget;

import org.robovm.apple.uikit.UIViewController;

public final class IosBorderViewController
    extends UIViewController
{
    public static IosBorderViewController of(UIViewController center) {
        IosBorderViewController controller = new IosBorderViewController();
        return controller;
    }

    public IosBorderViewController north(UIViewController north) {
        return this;
    }

    public IosBorderViewController west(UIViewController west) {
        return this;
    }

    public IosBorderViewController east(UIViewController east) {
        return this;
    }
}
