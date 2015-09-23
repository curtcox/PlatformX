package x.services;

public interface XLocationService {

    void selectLocation(XLocation selected);

    XLocation getCurrentLocation();

    int calculateDistance(XLocation location, XLocation currentLocation);
}
