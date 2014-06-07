package com.mycompany.myapp.screens;

import com.codename1.ui.Component;
import com.codename1.ui.Label;
import static com.mycompany.myapp.screens.SearchScreen.of;
import com.mycompany.myapp.ui.ActionButton;

/**
 *
 * @author Curt
 */
final class ZoomOut {
    
    private final Screen previous;
    final int radius;

    ZoomOut(Screen previous, int radius) {
        this.previous = previous;
        this.radius = radius;
    }

    Component createComponent() {
        return couldZoomOut() ? newZoomOutButton() : newZoomLabel();
    }

    boolean couldZoomOut() {
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
            public Screen create() { return of(previous, radius * 4); }
        };
    }

    String friendlyMeters() {
        return (radius<1000) ? radius + "m" : radius / 1000 + "K";     
    }

    ZoomOut zoomOut() {
        return new ZoomOut(previous,radius*4);
    }

}
