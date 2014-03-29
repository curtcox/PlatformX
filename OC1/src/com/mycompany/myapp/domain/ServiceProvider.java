package com.mycompany.myapp.domain;

import com.codename1.location.Location;
import com.mycompany.myapp.services.Locations;
import java.util.List;

/**
 *
 * @author Curt
 */
public final class ServiceProvider {
    
    public final Name name;
    public final Location location;
    public Rating myRating;
    public final List<Rating> ratings;
    
    public ServiceProvider(Name name, Location location, Rating myRating, List<Rating> ratings) {
        this.name = name;
        this.location = location;
        this.ratings = ratings;
        this.myRating = myRating;
    }
    
    @Override
    public String toString() {
        return name.toString() + " " + myRating + " - " + distanceFromCurrentLocation();
    }

    private String distanceFromCurrentLocation() {
        int miles = (int) Locations.calculateDistance(location,Locations.getCurrentLocation());
        return miles + " miles";
    }
}
