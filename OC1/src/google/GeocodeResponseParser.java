package google;

import com.codename1.io.JSONParser;
import oc1.log.Log;
import oc1.log.LogManager;
import oc1.ui.Icons;
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
final class GeocodeResponseParser {
    
    List<Location> parseJsonResponse(InputStreamReader reader) {
        List<Location> location = new ArrayList<Location>();
        try {
            JSONParser parser = new JSONParser();
            for (Map<String,Object> result : results(parser.parseJSON(reader))) {
                location.add(construct(result));
            }
            return location;
        } catch (IOException e) {
            log(e);
        } catch (RuntimeException e) {
            log(e);
        }
        return location;
    } 
    
    Iterable<Map<String,Object>> results(Map<String,Object> tree) {
        if (tree.isEmpty()) {
            log("Geocode Response Parser -- No results found -- failed request");
            return new ArrayList();
        }
        return (List)tree.get("results");
    }
    
    Location construct(Map<String,Object> map) {
        Location location  = new Location();
        location.address   = stringFrom(map,"formatted_address");
        Geometry geometry  = Geometry.of(map);
        location.type      = geometry.locationType;
        location.latitude  = geometry.latitude;
        location.longitude = geometry.longitude;
        location.types     = (String[]) ((List) map.get("types")).toArray(new String[0]);
        return location;
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
        return LogManager.of().getLog(GeocodeResponseParser.class);    
    }

}
