package google;

import x.json.JsonMap;

import java.util.Map;

final class Geometry {
    Double latitude;
    Double longitude;
    String locationType;
    static Geometry of(JsonMap json) {
        Geometry geo = new Geometry();
        JsonMap geometry = json.map("geometry");
        JsonMap location = geometry.map("location");
        geo.latitude = location.doubleValue("lat");
        geo.longitude = location.doubleValue("lng");
        geo.locationType = json.get("location_type").toString();
        return geo;
    }
}
