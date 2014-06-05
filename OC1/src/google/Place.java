package google;

import java.net.URI;

/**
 * See https://developers.google.com/places/documentation/search#PlaceSearchResults
 * @author Curt
 */
public final class Place {
    public String name;
    public String id;
    public String reference;
    public Double latitude;
    public Double longitude;
    public URI icon;
    public Boolean open_now;
    public Double price_level;
    public Double rating;
    public String[] types;
    public String vicinity;
    public String address;

    @Override
    public String toString() {
        return 
               " name=" + name +
               " latitude=" + latitude + 
               " longitude=" + longitude + 
               " price_level=" + price_level +
               " rating=" + rating +
               " address=" + address
       ; 
    }
}