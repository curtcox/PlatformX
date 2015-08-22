package ios.uiwidget;

import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;

public final class IosBorderViewController
    extends UIViewController
{
    public static IosBorderViewController of(UIView center) {
        IosBorderViewController controller = new IosBorderViewController();
        return controller;
    }

    public IosBorderViewController north(UIViewController north) {
        return this;
    }

    public IosBorderViewController west(UIView west) {
        return this;
    }

    public IosBorderViewController east(UIView east) {
        return this;
    }
}
