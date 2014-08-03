package oc1.domain;

import com.codename1.location.Location;

/**
 *
 * @author Curt
 */
public final class LocationDescription {

    public final String address;
    private final Double latitude;
    private final Double longitude;
    
    public LocationDescription(String address, Double latitude, Double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location toLocation() {
        Location location = new Location();
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        return location;
    }

    @Override
    public String toString() {
        return 
               " address=" + address +
               " latitude=" + latitude + 
               " longitude=" + longitude
       ; 
    }
    
}
