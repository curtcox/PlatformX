package ios.uiwidget;

import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIView;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;

public class IosPassthruView
        extends UIView
{
    @Override
    public boolean isPointInside(CGPoint point, UIEvent event) {
        //log("is point inside ? " + point + " event = " + event);
        return true;
    }

    public UIView hitTest(CGPoint point, UIEvent event) {
        //log("hit test ? " + point + " event = " + event);
        return super.hitTest(point,event);
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IosPassthruView.class);
    }
}
