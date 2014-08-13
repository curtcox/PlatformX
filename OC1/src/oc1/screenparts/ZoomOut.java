package oc1.screenparts;

import oc1.domain.Type;

/**
 *
 * @author Curt
 */
public final class ZoomOut {
    
    public final int radius;
    private final Type[] types;

    public ZoomOut(Type[] types, int radius) {
        this.radius = radius;
        this.types = types;
    }

    public String zoomText() {
        return couldZoomOut() ? zoomOutText() : maxZoomText();
    }

    public boolean couldZoomOut() {
        return bigger(radius) < 30000;
    }

    private String maxZoomText() {
        return friendlyMeters() + " (Max)";
    }

    private String zoomOutText() {
        return friendlyMeters() + " +";
    }

    private String friendlyMeters() {
        return (radius<1000) ? radius + "m" : radius / 1000 + "K";     
    }

    public ZoomOut zoomOut() {
        return new ZoomOut(types,bigger(radius));
    }

    private int bigger(int radius) {
        return radius * 4;
    }

}
