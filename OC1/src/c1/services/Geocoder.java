package c1.services;

import common.services.IGeocoder;
import google.Geocoding;
import google.GoogleLocation;
import java.util.ArrayList;
import java.util.List;
import common.Registry;
import common.domain.LocationDescription;
import c1.event.LiveList;
import c1.event.SimpleLiveList;

public final class Geocoder
    implements IGeocoder
{
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
        return new LocationDescription(place.address,place.latitude, place.longitude );
    }

}
