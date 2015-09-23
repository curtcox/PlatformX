package x.services;

import google.Place;
import google.PlacesSearch;
import x.app.Registry;
import x.domain.*;
import x.stores.MyRatings;

import java.util.ArrayList;
import java.util.List;

public final class XConsumerServiceProviders {
    
    private final PlacesSearch places = new PlacesSearch();
    
    public static XConsumerServiceProviders of() {
        return Registry.get(XConsumerServiceProviders.class);
    }
    
    public List<ConsumerServiceProvider> nearby(Type[] types, int radius) {
        List<ConsumerServiceProvider> providers = new ArrayList<ConsumerServiceProvider>();
        for (Place place : placesNearHere(types,radius)) {
            providers.add(serviceProviderFromPlace(place));
        }
        return providers;
    }

    private List<Place> placesNearHere(Type[] types, int radius) {
        XLocation currentLocation = locations().getCurrentLocation();
        if (currentLocation==null) {
            return new ArrayList<Place>();
        }
        double latitude = currentLocation.latitude;
        double longitude = currentLocation.longitude;
        return places.nearbySearch(latitude, longitude, radius,asStrings(types));
    }

    private XLocationService locations() {
        return Registry.get(XLocationService.class);
    }

    private String[] asStrings(Type[] types) {
        List<String> list = new ArrayList();
        for (Type type : types) {
            list.add(type.toString());
        }
        return list.toArray(new String[0]);
    }
    
    private ConsumerServiceProvider serviceProviderFromPlace(Place place) {
        ID id = new ID(place.id);
        return new ConsumerServiceProvider(
            id, new Name(place.name), locationFromPlace(place),
            new Address(place.vicinity),
            place.price_level,place.rating,typesFromPlace(place),place.icon,
            MyRatings.of().getFor(id)
        );
    }

    private XLocation locationFromPlace(Place place) {
        return new XLocation(place.latitude,place.longitude);
    }

    private Type[] typesFromPlace(Place place) {
        List<Type> list = new ArrayList();
        for (String type : place.types) {
            list.add(new Type(type));
        }
        return list.toArray(new Type[0]);
    }
}
