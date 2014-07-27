package google;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oc1.app.Registry;
import oc1.net.Network;

/**
 *
 * @author Curt
 */
public final class Geocoding {
    
    public List<Location> searchFor(String place) {
        Map<String,String> parameters = new HashMap();
        parameters.put("key",Google.API_key);
        parameters.put("address",place);
        URI url = GoogleUrl.of("https://maps.googleapis.com/maps/api/geocode/json?",parameters);
        return searchForPlaces(url);
    }

    private List<Location> searchForPlaces(URI url) {
        InputStream in = Registry.get(Network.class).getStreamFor(url);
        return parseJsonResponse(new InputStreamReader(in));
    } 
    
    private List<Location> parseJsonResponse(InputStreamReader reader) {
        GeocodeResponseParser parser = new GeocodeResponseParser();
        return parser.parseJsonResponse(reader);
    } 

}
