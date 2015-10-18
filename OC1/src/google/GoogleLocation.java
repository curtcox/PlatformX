package google;

import x.json.JsonMap;

import java.util.List;

/**
 * https://developers.google.com/maps/documentation/geocoding/
 */
public final class GoogleLocation {
    public final String address;
    public final String type;
    public final Double latitude;
    public final Double longitude;
    public final String[] types;

    private GoogleLocation(JsonMap map) {
        address   = map.string("formatted_address");
        Geometry geometry  = Geometry.of(map);
        type      = geometry.locationType;
        latitude  = geometry.latitude;
        longitude = geometry.longitude;
        types     = (String[]) ((List) map.get("types")).toArray(new String[0]);
    }

    static final class Constructor
            implements JsonItemConstructor<GoogleLocation>
    {
        public GoogleLocation construct(JsonMap map) {
            return new GoogleLocation(map);
        }
    }

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
