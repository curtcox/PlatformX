package com.mycompany.myapp.domain;

import com.codename1.location.Location;
import com.mycompany.myapp.services.Locations;
import java.util.List;

/**
 *
 * @author Curt
 */
public final class ServiceProvider {
    
    public final ID id;
    public final Name name;
    public final Location location;
    public Rating myRating;
    public final List<Rating> ratings;
    private final Locations locations = Locations.of(); 
    
    public ServiceProvider(ID id, Name name, Location location, Rating myRating, List<Rating> ratings) {
        this.id = id;
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
        int miles = (int) locations.calculateDistance(location,locations.getCurrentLocation());
        return miles + " miles";
    }
}
