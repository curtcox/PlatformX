package google;

import x.json.JsonMap;

import java.util.Map;

final class Geometry {
    Double latitude;
    Double longitude;
    String locationType;
    static Geometry of(JsonMap map) {
        Geometry geo = new Geometry();
        map = (JsonMap) map.get("geometry");
        Map<String,Object> location = (Map<String,Object>) map.get("location");
        geo.latitude = (Double) location.get("lat");
        geo.longitude = (Double) location.get("lng");
        geo.locationType = map.get("location_type").toString();
        return geo;
    }
}
