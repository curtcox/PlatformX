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

    public IosBorderContainer north(UIView component) {
        return this;
    }

    public IosBorderContainer east(UIView component) {
        return this;
    }

    public IosBorderContainer west(UIView component) {
        return this;
    }

    public IosBorderContainer layout() {
        return this;
    }
}
