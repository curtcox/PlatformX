package oc1.services;

import google.Geocoding;
import google.GoogleLocation;
import java.util.ArrayList;
import java.util.List;
import oc1.app.Registry;
import oc1.domain.LocationDescription;
import oc1.event.LiveList;
import oc1.event.SimpleLiveList;

/**
 *
 * @author Curt
 */
public final class Geocoder {

    private final Geocoding geocoding = new Geocoding();

    public static Geocoder of() {
        return Registry.get(Geocoder.class);
    }
    
    public LiveList<LocationDescription> searchFor(String place) {
        List<LocationDescription> locations = new ArrayList<LocationDescription>();
        for (GoogleLocation location : geocoding.searchFor(place)) {
            locations.add(locationFromGoogle(location));
        }
        return new SimpleLiveList(locations);
    }

    private LocationDescription locationFromGoogle(GoogleLocation place) {
        return new LocationDescription(place.latitude, place.longitude );
    }

}
