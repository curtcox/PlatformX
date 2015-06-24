package c1.services;

import google.Geocoding;
import google.GoogleLocation;
import x.Registry;
import x.domain.LocationDescription;
import x.event.LiveList;
import x.event.XLiveList;
import x.services.IGeocoder;

import java.util.ArrayList;
import java.util.List;

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
        return new XLiveList(locations);
    }

    private LocationDescription locationFromGoogle(GoogleLocation place) {
        return new LocationDescription(place.address,place.latitude, place.longitude );
    }

}
