package oc1.stores;

import oc1.domain.Address;
import oc1.domain.ID;
import oc1.domain.Name;
import com.codename1.location.Location;
import oc1.app.Registry;
import oc1.domain.ServiceProvider;
import oc1.event.LiveList;
import oc1.event.SimpleLiveList;
import oc1.services.Locations;
import google.Place;
import google.PlacesSearch;
import java.util.ArrayList;
import java.util.List;
import oc1.domain.Type;

/**
 *
 * @author Curt
 */
public final class ServiceProviders {
    
    private final PlacesSearch places = new PlacesSearch();
    
    public static ServiceProviders of() {
        return Registry.get(ServiceProviders.class);
    }
    
    public LiveList<ServiceProvider> nearby(int radius, Type[] types) {
        List<ServiceProvider> providers = new ArrayList<ServiceProvider>();
        for (Place place : placesNearHere(radius,types)) {
            providers.add(serviceProviderFromPlace(place));
        }
        return new SimpleLiveList(providers);
    }

    public List<Place> placesNearHere(int radius,Type[] types) {
        Location currentLocation = Locations.of().getCurrentLocation();
        if (currentLocation==null) {
            return new ArrayList<Place>();
        }
        double latitude = currentLocation.getLatitude();
        double longitude = currentLocation.getLongitude();
        return places.nearbySearch(latitude, longitude, radius,asStrings(types));
    }

    private String[] asStrings(Type[] types) {
        List<String> list = new ArrayList();
        for (Type type : types) {
            list.add(type.toString());
        }
        return list.toArray(new String[0]);
    }
    
    private ServiceProvider serviceProviderFromPlace(Place place) {
        ID id = new ID(place.id);
        return new ServiceProvider(
            id, new Name(place.name), locationFromPlace(place),
            new Address(place.vicinity),
            place.price_level,place.rating,typesFromPlace(place),place.icon,
            MyRatings.of().getFor(id)
        );
    }

    private Location locationFromPlace(Place place) {
        Location location = new Location();
        location.setLatitude(place.latitude);
        location.setLongitude(place.longitude);
        return location;
    }

    private Type[] typesFromPlace(Place place) {
        List<Type> list = new ArrayList();
        for (String type : place.types) {
            list.add(new Type(type));
        }
        return list.toArray(new Type[0]);
    }
}
