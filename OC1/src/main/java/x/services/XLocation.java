package x.services;

public final class XLocation {

    public interface Listener {
        void onChange(XLocation reading);
    }

    public final double latitude;
    public final double longitude;

    public XLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
