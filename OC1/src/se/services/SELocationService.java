package se.services;

import x.services.LocationReading;
import x.services.LocationService;

public final class SELocationService
    implements LocationService
{
    @Override
    public void selectLocation(LocationReading selected) {

    }

    @Override
    public LocationReading getCurrentLocation() {
        return null;
    }

    @Override
    public int calculateDistance(LocationReading location, LocationReading currentLocation) {
        return 0;
    }
}
