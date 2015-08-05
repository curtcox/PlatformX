package ios.uiwidget;

import org.robovm.apple.uikit.UIView;

public final class IosBorderContainer
    extends IosColumnContainer
{
    private final UIView center;
    private UIView north;
    private UIView east;
    private UIView west;
    private IosRowContainer westCenterEast;

    private IosBorderContainer(UIView center) {
        this.center = center;
        westCenterEast = new IosRowContainer();
    }

    public static IosBorderContainer of(UIView center) {
        return new IosBorderContainer(center);
    }

    public IosBorderContainer north(UIView north) {
        this.north = north;
        return this;
    }

    public IosBorderContainer east(UIView east) {
        this.east = east;
        return this;
    }

    public IosBorderContainer west(UIView west) {
        this.west = west;
        return this;
    }

    public IosBorderContainer layout() {
        if (north!=null) {
            addSubview(north);
        }
        layoutCenterRow();
        return this;
    }

    private void layoutCenterRow() {
        if (west!=null) {
            westCenterEast.addSubview(west);
        }
        westCenterEast.addSubview(center);
        if (east!=null) {
            westCenterEast.addSubview(east);
        }
        addSubview(westCenterEast);
    }
}
