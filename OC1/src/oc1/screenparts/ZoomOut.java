package oc1.screenparts;

import com.codename1.ui.Component;
import com.codename1.ui.Label;
import oc1.domain.Type;
import oc1.screen.Screen;
import oc1.screen.ScreenButton;
import oc1.ui.ActionButton;

/**
 *
 * @author Curt
 */
public final class ZoomOut {
    
    private final Screen previous;
    public final int radius;
    private final Type[] types;

    public ZoomOut(Screen previous, Type[] types, int radius) {
        this.previous = previous;
        this.radius = radius;
        this.types = types;
    }

    public Component createComponent() {
        return couldZoomOut() ? newZoomOutButton() : newZoomLabel();
    }

    public boolean couldZoomOut() {
        return bigger(radius) < 30000;
    }

    Label newZoomLabel() {
        return new Label(friendlyMeters() + " (Max)");
    }

    ActionButton newZoomOutButton() {
        return ScreenButton.lazyWithTextAndLeadingTo(friendlyMeters() + " +","SearchScreen",previous,types,bigger(radius));
    }

    String friendlyMeters() {
        return (radius<1000) ? radius + "m" : radius / 1000 + "K";     
    }

    public ZoomOut zoomOut() {
        return new ZoomOut(previous,types,bigger(radius));
    }

    private int bigger(int radius) {
        return radius * 4;
    }

}
