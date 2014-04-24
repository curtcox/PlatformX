package google;

import com.mycompany.myapp.net.Network;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * See https://developers.google.com/places/documentation/search
 * @author Curt
 */
public final class PlacesSearch {
    
    private final Network network = new Network();
    private static final String API_key = "";
    
    public List<Place> nearbySearch(double latitude, double longitude) {
        Map<String,String> parameters = new HashMap();
        parameters.put("key",API_key);
        parameters.put("radius","50000");
        parameters.put("location",latitude + "," + longitude);
        parameters.put("sensor","true");
        String url = GoogleUrl.of("https://maps.googleapis.com/maps/api/place/nearbysearch/json?",parameters);
        return searchForPlaces(url);
    }

    public List<Place> textSearch() {
        throw new RuntimeException();
    }

    public List<Place> radarSearch(String query, double latitude, double longitude) {
        throw new RuntimeException();
    }

    private List<Place> searchForPlaces(String url) {
        InputStream in = network.getStreamFor(url);
        return parseJsonResponse(new InputStreamReader(in));
    } 
    
    private List<Place> parseJsonResponse(InputStreamReader reader) {
        PlacesResponseParser parser = new PlacesResponseParser();
        return parser.parseJsonResponse(reader);
    } 
    
}
