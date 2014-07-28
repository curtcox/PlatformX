package google;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * See https://developers.google.com/maps/documentation/geocoding/
 * @author Curt
 */
public final class Geocoding
    extends WebServiceSearch
{
    public Geocoding() {
        super("https://maps.googleapis.com/maps/api/geocode/json?",new GeocodeResponseParser());
    }
    
    public List<Location> searchFor(String place) {
        return searchForPlaces(getURI(mapParams(place)));
    }

    private Map<String,String> mapParams(String place) {
        Map<String,String> parameters = new HashMap();
        parameters.put("address",place);
        return parameters;
    }
}
