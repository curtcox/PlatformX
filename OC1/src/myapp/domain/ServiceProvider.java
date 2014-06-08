package myapp.domain;

import com.codename1.location.Location;
import myapp.Registry;
import myapp.services.Locations;
import myapp.ui.StringSource;
import java.net.URI;

/**
 *
 * @author Curt
 */
public final class ServiceProvider {
    
    public final ID id;
    public final Name name;
    public final Location location;
    public final Address address;
    public final Double priceLevel;
    public final String[] types;
    public final URI icon;
    private Rating myRating;
    
    public static final ServiceProvider NULL = new ServiceProvider(null,new Name(""),null,null,null,null,null,new Rating(""));
    
    public ServiceProvider(ID id, Name name, Location location, Address address, Double priceLevel, String[] types, URI icon, Rating myRating) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.address = address;
        this.myRating = myRating;
        this.priceLevel = priceLevel;
        this.types = types;
        this.icon = icon;
    }

    public Rating myRating() {
        return myRating;
    }

    public void rate(Rating rating) {
        myRating = rating;
    }

    @Override
    public String toString() {
        return name.toString() + " " + myRating + " - " + distanceFromCurrentLocation();
    }

    public String distanceFromCurrentLocation() {
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

    public static StringSource getCurrentRatingSource() {
        return new StringSource() {
            public String getString() {
                String rating = getCurrentRating();
                return rating.length()==0 ? "Rate" : rating;
            }
        };
    }

    public static String getCurrentRating() {
        return ServiceProvider.getSelected().myRating.toString();
    }

}
