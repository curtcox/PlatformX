package google;

import x.json.JsonMap;
import x.json.JsonValue;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * See https://developers.google.com/places/documentation/search#PlaceSearchResults
 */
public final class Place {

    public final String name;
    public final String id;
    public final String reference;
    public final Double latitude;
    public final Double longitude;
    public final URI icon;
    public final Boolean open_now;
    public final Long price_level;
    public final Double rating;
    public final String[] types;
    public final String vicinity;
    public final String address;

    private Place(JsonMap map) {
        name        = map.string("name");
        address     = map.string("formatted_address");
        vicinity    = map.string("vicinity");
        id          = map.string("place_id");
        reference   = map.string("reference");
        Geometry geometry = Geometry.of(map.map("geometry"));
        latitude    = geometry.latitude;
        longitude   = geometry.longitude;
        open_now    = map.map("opening_hours").booleanValue("open_now");
        icon        = map.uri("icon");
        price_level = map.longValue("price_level");
        rating      = map.doubleValue("rating");
        types       = placeTypes(map);
    }

    static final class Constructor
            implements JsonItemConstructor<Place>
    {
        public Place construct(JsonMap map) {
            return new Place(map);
        }
    }

    String[] placeTypes(JsonMap map) {
        List<String> list = new ArrayList();
        for (Object o : map.list("types")) {
            JsonValue value = (JsonValue) o;
            list.add(value.toString());
        }
        return list.toArray(new String[0]);
    }

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