package ios.uiwidget;

import org.robovm.apple.uikit.UIView;

public final class IosBorderContainer
    extends UIView
{
    private IosBorderContainer() {
    }

    public static IosBorderContainer of(UIView view) {
        return new IosBorderContainer();
    }

    public IosBorderContainer addNorth(UIView component) {
        return this;
    }

    public IosBorderContainer addEast(UIView component) {
        return this;
    }
}
