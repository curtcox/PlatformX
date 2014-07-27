package google;

import java.util.Map;

/**
 *
 * @author Curt
 */
final class Geometry {
    Double latitude;
    Double longitude;
    String locationType;
    static Geometry of(Map<String,Object> map) {
        Geometry geo = new Geometry();
        map = (Map<String, Object>) map.get("geometry");
        Map<String,Object> location = (Map<String,Object>) map.get("location");
        geo.latitude = (Double) location.get("lat");
        geo.longitude = (Double) location.get("lng");
        geo.locationType = (String) map.get("location_type");
        return geo;
    }
}
