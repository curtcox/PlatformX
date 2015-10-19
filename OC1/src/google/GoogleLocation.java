package google;

import x.json.JsonMap;
import x.json.JsonValue;

import java.util.ArrayList;
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
        Geometry geometry = geometry(map);
        type      = geometry==null ? null : geometry.locationType;
        latitude  = geometry==null ? null : geometry.latitude;
        longitude = geometry==null ? null : geometry.longitude;
        types     = types(map);
    }

    static String[] types(JsonMap map) {
        List<String> list = new ArrayList<String>();
        if (map.containsKey("types")) {
            for (Object o : map.list("types")) {
                JsonValue value = (JsonValue) o;
                list.add(value.toString());
            }
        }
        return list.toArray(new String[0]);
    }

    static Geometry geometry(JsonMap map) {
        return map.containsKey("geometry") ? Geometry.of(map.map("geometry")) : null;
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
