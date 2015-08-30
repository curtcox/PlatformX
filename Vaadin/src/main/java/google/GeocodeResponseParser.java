package google;

import java.util.List;
import java.util.Map;

final class GeocodeResponseParser
    extends JsonResponseParser
{
    GoogleLocation construct(Map map) {
        GoogleLocation location  = new GoogleLocation();
        location.address   = stringFrom(map,"formatted_address");
        Geometry geometry  = Geometry.of(map);
        location.type      = geometry.locationType;
        location.latitude  = geometry.latitude;
        location.longitude = geometry.longitude;
        location.types     = (String[]) ((List) map.get("types")).toArray(new String[0]);
        return location;
    }
}
