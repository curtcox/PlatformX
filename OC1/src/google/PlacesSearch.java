package google;

import oc1.app.Registry;
import oc1.net.Network;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * See https://developers.google.com/places/documentation/search
 * @author Curt
 */
public final class PlacesSearch {
    
    private static final String OR = "%7C";
    
    public List<Place> nearbySearch(double latitude, double longitude, int radius, String[] types) {
        Map<String,String> parameters = new HashMap();
        parameters.put("key",Google.API_key);
        parameters.put("radius",Integer.toString(radius));
        parameters.put("location",latitude + "," + longitude);
        parameters.put("sensor","true");
        if (types.length == 1) {
            parameters.put("types",types[0]);
        }
        if (types.length > 1) {
            parameters.put("types",pipeSeparated(types));
        }
        URI url = GoogleUrl.of("https://maps.googleapis.com/maps/api/place/nearbysearch/json?",parameters);
        return searchForPlaces(url);
    }

    public List<Place> textSearch() {
        throw new RuntimeException();
    }

    public List<Place> radarSearch(String query, double latitude, double longitude) {
        throw new RuntimeException();
    }

    private List<Place> searchForPlaces(URI url) {
        InputStream in = Registry.get(Network.class).getStreamFor(url);
        return parseJsonResponse(new InputStreamReader(in));
    } 
    
    private List<Place> parseJsonResponse(InputStreamReader reader) {
        PlacesResponseParser parser = new PlacesResponseParser();
        return parser.parseJsonResponse(reader);
    } 

    public Place details(String reference) {
        Map<String,String> parameters = new HashMap();
        parameters.put("key",Google.API_key);
        parameters.put("reference",reference);
        parameters.put("sensor","true");
        URI url = GoogleUrl.of("https://maps.googleapis.com/maps/api/place/details/json?",parameters);
        return searchForDetails(url);
    }

    private Place searchForDetails(URI url) {
        throw new RuntimeException();
    } 

    private String pipeSeparated(String[] strings) {
        StringBuilder out = new StringBuilder();
        for (String string : strings) {
            out.append(string);
            out.append(OR);
        }
        return out.toString().substring(0, out.length() - OR.length());
    }

}
