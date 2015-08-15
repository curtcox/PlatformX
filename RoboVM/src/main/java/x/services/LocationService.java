package x.services;

public interface LocationService {

    void selectLocation(LocationReading selected);

    LocationReading getCurrentLocation();

    int calculateDistance(LocationReading location, LocationReading currentLocation);
}
