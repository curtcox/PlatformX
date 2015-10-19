package google;

import x.json.JsonMap;

final class Geometry {

    final Double latitude;
    final Double longitude;
    final String locationType;

    Geometry(JsonMap json) {
        JsonMap location = json.map("location");
        latitude = location==null ? null : location.doubleValue("lat");
        longitude = location==null ? null : location.doubleValue("lng");
        locationType = json.string("location_type");
    }

    static Geometry of(JsonMap json) {
        return new Geometry(json);
    }
}
