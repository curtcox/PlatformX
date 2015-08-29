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
        log("isPointInside",point,event);
        return true;
    }

    public UIView hitTest(CGPoint point, UIEvent event) {
        log("hitTest",point,event);
        return super.hitTest(point,event);
    }

    private void log(String method, CGPoint point, UIEvent event) {
        // getLog().log(method + " point=" + point + " event=" +event);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IosPassthruView.class);
    }
}
