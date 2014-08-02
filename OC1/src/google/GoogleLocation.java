package google;

/**
 * https://developers.google.com/maps/documentation/geocoding/
 * @author Curt
 */
public final class GoogleLocation {
    public String address;
    public String type;
    public Double latitude;
    public Double longitude;
    public String[] types;
    
    @Override
    public String toString() {
        return 
               " address=" + address +
               " latitude=" + latitude + 
               " longitude=" + longitude + 
               " type=" + type
       ; 
    }
}
