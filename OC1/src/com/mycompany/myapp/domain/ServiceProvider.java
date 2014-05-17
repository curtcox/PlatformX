package com.mycompany.myapp.domain;

import com.codename1.location.Location;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.services.Locations;
import com.mycompany.myapp.ui.StringSource;

/**
 *
 * @author Curt
 */
public final class ServiceProvider {
    
    public final ID id;
    public final Name name;
    public final Location location;
    private Rating myRating;
    
    public ServiceProvider(ID id, Name name, Location location, Rating myRating) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.myRating = myRating;
    }

    public void rate(Rating rating) {
        myRating = rating;
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
