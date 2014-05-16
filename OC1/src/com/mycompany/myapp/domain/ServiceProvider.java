package com.mycompany.myapp.domain;

import com.codename1.location.Location;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.services.Locations;
import com.mycompany.myapp.ui.StringSource;
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
        Locations locations = Locations.of();
        int miles = (int) locations.calculateDistance(location,locations.getCurrentLocation());
        return miles + " miles";
    }
    
    public static ServiceProvider getSelected() {
        return Registry.get(ServiceProvider.class);
    }
    
    public static StringSource getCurrentName() {
        return new StringSource() {
            public String getString() {
                return ServiceProvider.getSelected().name.toString();
            }
        };
    }

    public static StringSource getCurrentRating() {
        return new StringSource() {
            public String getString() {
                return ServiceProvider.getSelected().myRating.toString();
            }
        };
    }

}
