package x.services;

public interface XLocationProvider {
    XLocation getLastKnownLocation();
    void setLocationListener(XLocation.Listener listener);
}
