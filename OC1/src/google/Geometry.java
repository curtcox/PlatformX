package google;

import x.json.JsonMap;

final class Geometry {
    Double latitude;
    Double longitude;
    String locationType;
    static Geometry of(JsonMap json) {
        Geometry geo = new Geometry();
        JsonMap location = json.map("location");
        geo.latitude = location.doubleValue("lat");
        geo.longitude = location.doubleValue("lng");
        geo.locationType = json.string("location_type");
        return geo;
    }
}
