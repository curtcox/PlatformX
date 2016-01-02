package x.services;

import google.Geocoding;
import google.GoogleLocation;
import x.app.Registry;
import x.domain.LocationDescription;
import x.event.LiveList;
import x.event.XLiveList;

import java.util.ArrayList;
import java.util.List;

public final class XGeocoder {

    private final Geocoding geocoding = new Geocoding();

    public static XGeocoder of() {
        return Registry.get(XGeocoder.class);
    }
    
    public LiveList<LocationDescription> searchFor(String place) {
        List<LocationDescription> locations = new ArrayList<LocationDescription>();
        for (GoogleLocation location : geocoding.searchFor(place)) {
            locations.add(locationFromGoogle(location));
        }
        return XLiveList.of(locations);
    }

    private LocationDescription locationFromGoogle(GoogleLocation place) {
        return new LocationDescription(place.address,place.latitude, place.longitude );
    }

}
