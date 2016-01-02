package x.domain;

import x.app.Registry;
import x.event.StringSource;
import x.services.XDistanceCalculator;
import x.services.XLocation;
import x.services.XLocationService;

import java.net.URI;

public final class ConsumerServiceProvider {
    
    public final ID id;
    public final Name name;
    public final XLocation location;
    public final Address address;
    public final Long priceLevel;
    public final Double rating;
    public final Type[] types;
    public final URI icon;
    private Rating myRating;
    
    public static final ConsumerServiceProvider NULL = new ConsumerServiceProvider(null,new Name(""),null,null,null,null,null,null,new Rating(""));
    
    public ConsumerServiceProvider(
            ID id, Name name, XLocation location, Address address,
            Long priceLevel, Double rating,
            Type[] types, URI icon, Rating myRating)
    {
        this.id = id;
        this.name = name;
        this.location = location;
        this.address = address;
        this.myRating = myRating;
        this.priceLevel = priceLevel;
        this.rating = rating;
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
        XLocationService locations = locationService();
        int miles = XDistanceCalculator.calculateDistance(location, locations.getCurrentLocation());
        return miles + " miles";
    }

    private static XLocationService locationService() {
        return Registry.get(XLocationService.class);
    }

    public static ConsumerServiceProvider getSelected() {
        return Registry.get(ConsumerServiceProvider.class);
    }
    
    public static StringSource getCurrentName() {
        return new StringSource() {
            public String getString() {
                return ConsumerServiceProvider.getSelected().name.toString();
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
        return ConsumerServiceProvider.getSelected().myRating.toString();
    }

}
