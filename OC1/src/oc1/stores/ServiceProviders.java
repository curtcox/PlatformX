package oc1.stores;

import oc1.domain.Address;
import oc1.domain.ID;
import oc1.domain.Name;
import com.codename1.location.Location;
import oc1.app.Registry;
import oc1.domain.Rating;
import oc1.domain.ServiceProvider;
import oc1.event.LiveList;
import oc1.event.SimpleLiveList;
import oc1.services.Locations;
import google.Place;
import google.PlacesSearch;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Curt
 */
public final class ServiceProviders {
    
    private final PlacesSearch places = new PlacesSearch();
    
    public static ServiceProviders of() {
        return Registry.get(ServiceProviders.class);
    }
    
    public LiveList<ServiceProvider> nearby(int radius) {
        List<ServiceProvider> providers = new ArrayList<ServiceProvider>();
        for (Place place : placesNearHere(radius)) {
            providers.add(serviceProviderFromPlace(place));
        }
        return new SimpleLiveList(providers);
    }

    public List<Place> placesNearHere(int radius) {
        Location currentLocation = Locations.of().getCurrentLocation();
        if (currentLocation==null) {
            return new ArrayList<Place>();
        }
        double latitude = currentLocation.getLatitude();
        double longitude = currentLocation.getLongitude();
        return places.nearbySearch(latitude, longitude, radius);
    }

    private ServiceProvider serviceProviderFromPlace(Place place) {
        ID id = new ID(place.id);
        return new ServiceProvider(
            id, new Name(place.name), locationFromPlace(place),
            new Address(place.vicinity),
            place.price_level,place.rating,place.types,place.icon,
            MyRatings.of().getFor(id)
        );
    }

    private Location locationFromPlace(Place place) {
        Location location = new Location();
        location.setLatitude(place.latitude);
        location.setLongitude(place.longitude);
        return location;
    }
}
