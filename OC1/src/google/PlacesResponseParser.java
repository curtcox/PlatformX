package google;

import com.codename1.io.JSONParser;
import com.mycompany.myapp.log.Log;
import com.mycompany.myapp.log.LogManager;
import com.mycompany.myapp.ui.Icons;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
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
            log(e);
        } catch (RuntimeException e) {
            log(e);
        }
        return places;
    } 
    
    Iterable<Map<String,Object>> results(Map<String,Object> tree) {
        if (tree.isEmpty()) {
            log("Places Response Parser -- No results found -- failed request");
            return new ArrayList();
        }
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
        place.name        = stringFrom(map,"name");
        place.address     = stringFrom(map,"formatted_address");
        place.vicinity    = stringFrom(map,"vicinity");
        place.id          = stringFrom(map,"id");
        place.reference   = stringFrom(map,"reference");
        Geometry geometry = Geometry.of(map);
        place.latitude    = geometry.latitude;
        place.longitude   = geometry.longitude;
        place.open_now    = null;
        place.icon        = uriFrom(map,"icon");
        place.price_level = doubleFrom(map,"price_level");
        place.rating      = doubleFrom(map,"rating");
        place.types       = (String[]) ((List) map.get("types")).toArray(new String[0]);
        return place;
    }
    
    String stringFrom(Map<String,Object> map, String key) {
        return (String) map.get(key);
    }

    Double doubleFrom(Map<String,Object> map, String key) {
        return (Double) map.get(key);
    }

    URI uriFrom(Map<String,Object> map, String key) {
        String string = stringFrom(map,key);
        try {
            URI uri = (string==null) ? null : new URI(string);
            Icons.of().getImage(uri);
            return uri;
        } catch (URISyntaxException e) {
            log(e);
            return null;
        }
    }
    
    private void log(Exception e) {
        getLog().log(e);    
    }

    private void log(String message) {
        getLog().log(message);    
    }

    private Log getLog() {
        return LogManager.of().getLog(PlacesResponseParser.class);    
    }

}
