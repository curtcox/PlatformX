package oc1.domain;

/**
 *
 * @author Curt
 */
public final class LocationDescription {

    private final Double latitude;
    private final Double longitude;
    
    public LocationDescription(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    @Override
    public String toString() {
        return 
               " latitude=" + latitude + 
               " longitude=" + longitude
       ; 
    }
    
}
