package google;

import com.codename1.io.JSONParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Curt
 */
public final class PlacesResponseParser {
    
    List<Place> parseJsonResponse(InputStreamReader reader) {
        List<Place> places = new ArrayList<Place>();
        try {
            JSONParser parser = new JSONParser();
            for (Map<String,Object> result : results(parser.parseJSON(reader))) {
                places.add(construct(result));
            }
            return places;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return places;
    } 
    
    Iterable<Map<String,Object>> results(Map<String,Object> tree) {
        return (List)tree.get("results");
    }
    
    static final class Geometry {
       Double latitude;
       Double longitude;
        static Geometry of(Map<String,Object> map) {
            Geometry geo = new Geometry();
            map = (Map<String, Object>) map.get("geometry");
            Map<String,Object> location = (Map<String,Object>) map.get("location");
            geo.latitude = (Double) location.get("lat");
            geo.longitude = (Double) location.get("lng");
            return geo;
        }
    }
    
    Place construct(Map<String,Object> map) {
        Place place = new Place();
        place.name = (String) map.get("name");
        place.address = "?";
        Geometry geometry = Geometry.of(map);
        place.latitude = geometry.latitude;
        place.longitude = geometry.longitude;
        place.open_now = null;
        place.icon = null;
        place.price_level = (Double) map.get("price_level");
        place.rating = (Double) map.get("rating");
        place.types = (String[]) ((List) map.get("types")).toArray(new String[0]);
        return place;
    }
}
