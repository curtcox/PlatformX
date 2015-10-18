package google;

import x.json.JsonMap;

import java.util.List;

final class GeocodeItemConstructor
    implements JsonItemConstructor<GoogleLocation>
{
    public GoogleLocation construct(JsonMap map) {
        GoogleLocation location  = new GoogleLocation();
        location.address   = map.string("formatted_address");
        Geometry geometry  = Geometry.of(map);
        location.type      = geometry.locationType;
        location.latitude  = geometry.latitude;
        location.longitude = geometry.longitude;
        location.types     = (String[]) ((List) map.get("types")).toArray(new String[0]);
        return location;
    }
}
