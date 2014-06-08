package myapp.stores;

import myapp.domain.Address;
import myapp.domain.ID;
import myapp.domain.Name;
import com.codename1.location.Location;
import myapp.Registry;
import myapp.domain.Rating;
import myapp.domain.ServiceProvider;
import myapp.event.LiveList;
import myapp.event.SimpleLiveList;
import myapp.services.Locations;
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
        double latitude = currentLocation.getLatitude();
        double longitude = currentLocation.getLongitude();
        return places.nearbySearch(latitude, longitude, radius);
    }

    private ServiceProvider serviceProviderFromPlace(Place place) {
        ID id = new ID(place.id);
        Name name = new Name(place.name);
        Location placeLocation = new Location();
        placeLocation.setLatitude(place.latitude);
        placeLocation.setLongitude(place.longitude);
        Rating myRating = MyRatings.of().getFor(id);
        return new ServiceProvider(id,name,placeLocation,new Address(place.vicinity),place.price_level,place.types,place.icon,myRating);
    }

}
