package google;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * See https://developers.google.com/places/documentation/search
 */
public final class PlacesSearch
    extends WebServiceSearch
{
    private static final String OR = "%7C";
    
    public PlacesSearch() {
        super("https://maps.googleapis.com/maps/api/place/nearbysearch/json?",new PlacesItemConstructor());
    }
    
    public List<Place> nearbySearch(double latitude, double longitude, int radius, String[] types) {
        return searchForPlaces(getURI(mapParams(latitude,longitude,radius,types)));
    }

    private Map<String,String> mapParams(double latitude, double longitude, int radius, String[] types) {
        Map<String,String> map = new HashMap();
        map.put("radius",Integer.toString(radius));
        map.put("location",latitude + "," + longitude);
        map.put("sensor","true");
        if (types.length == 1) {
            map.put("types",types[0]);
        }
        if (types.length > 1) {
            map.put("types",pipeSeparated(types));
        }
        return map;
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
