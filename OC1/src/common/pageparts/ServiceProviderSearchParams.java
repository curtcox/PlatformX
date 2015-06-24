package common.pageparts;

import common.domain.Type;

public final class ServiceProviderSearchParams {
    
    public final int radius;
    public final Type[] types;

    public ServiceProviderSearchParams(Type[] types, int radius) {
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

    public ServiceProviderSearchParams zoomOut() {
        return new ServiceProviderSearchParams(types,bigger(radius));
    }

    private int bigger(int radius) {
        return radius * 4;
    }

}
