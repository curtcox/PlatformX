package com.mycompany.myapp.stores;

import com.codename1.location.Location;
import com.mycompany.myapp.domain.Name;
import com.mycompany.myapp.domain.Rating;
import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.event.LiveList;
import com.mycompany.myapp.event.SimpleLiveList;
import com.mycompany.myapp.services.Locations;
import google.Place;
import google.PlacesSearch;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Curt
 */
public final class ServiceProviders {
    
    private final Locations locations = Locations.of();
    private final PlacesSearch places = new PlacesSearch();
    private static final ServiceProviders singleton = new ServiceProviders();
    
    private ServiceProviders() {}
    
    public static ServiceProviders of() {
        return singleton;
    }
    
    public LiveList<ServiceProvider> all() {
        return nearby();
    }

    public LiveList<ServiceProvider> nearby() {
        Location currentLocation = locations.getCurrentLocation();
        double latitude = currentLocation.getLatitude();
        double longitude = currentLocation.getLongitude();
        List<ServiceProvider> providers = new ArrayList<ServiceProvider>();
        for (Place place : places.nearbySearch(latitude, longitude)) {
            Name name = new Name(place.name);
            Location placeLocation = new Location();
            placeLocation.setLatitude(place.latitude);
            placeLocation.setLongitude(place.longitude);
            Rating myRating = new Rating("");
            List<Rating> ratings = new ArrayList();
            providers.add(new ServiceProvider(name,placeLocation,myRating,ratings));
        }
        return new SimpleLiveList(providers);
    }
    
    private static ServiceProvider newServiceProvider(String name, double lat, double lon) {
        Location location = new Location();
        location.setLatitude(lat);
        location.setLongitude(lon);
        List<Rating> ratings = new ArrayList();
        Rating myRating = new Rating("?");
        return new ServiceProvider(new Name(name),location,myRating,ratings);
    }
}
