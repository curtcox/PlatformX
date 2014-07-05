package oc1.screenparts;

import com.codename1.ui.Component;
import com.codename1.ui.Label;
import oc1.screen.Screen;
import oc1.screen.ScreenButton;
import oc1.screen.ScreenFactory;
import oc1.screens.SearchScreen;
import oc1.screens.SearchScreenFactory;
import oc1.ui.ActionButton;

/**
 *
 * @author Curt
 */
public final class ZoomOut {
    
    private final Screen previous;
    public final int radius;

    public ZoomOut(Screen previous, int radius) {
        this.previous = previous;
        this.radius = radius;
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
        return ScreenButton.lazyWithTextAndLeadingTo(friendlyMeters() + " +",newZoomOutLink());
    }

    ScreenFactory newZoomOutLink() {
        return new ScreenFactory() {
            public Screen create() { return SearchScreenFactory.withPreviousAndRadius(previous, bigger(radius)); }
        };
    }

    String friendlyMeters() {
        return (radius<1000) ? radius + "m" : radius / 1000 + "K";     
    }

    public ZoomOut zoomOut() {
        return new ZoomOut(previous,bigger(radius));
    }

    private int bigger(int radius) {
        return radius * 4;
    }

}
