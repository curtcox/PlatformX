package oc1.screen.parts;

import com.codename1.ui.Component;
import com.codename1.ui.Label;
import oc1.screens.Screen;
import oc1.screens.ScreenButton;
import oc1.screens.ScreenFactory;
import oc1.screens.SearchScreen;
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
        return radius * 4 < 30000;
    }

    Label newZoomLabel() {
        return new Label(friendlyMeters() + " (Max)");
    }

    ActionButton newZoomOutButton() {
        return ScreenButton.lazyWithTextAndLeadingTo(friendlyMeters() + " +",newZoomOutLink());
    }

    ScreenFactory newZoomOutLink() {
        return new ScreenFactory() {
            public Screen create() { return SearchScreen.of(previous, radius * 4); }
        };
    }

    String friendlyMeters() {
        return (radius<1000) ? radius + "m" : radius / 1000 + "K";     
    }

    public ZoomOut zoomOut() {
        return new ZoomOut(previous,radius*4);
    }

}
