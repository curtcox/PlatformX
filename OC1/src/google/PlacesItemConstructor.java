package google;

import x.json.JsonMap;

final class PlacesItemConstructor
    implements JsonItemConstructor<Place>
{
    public Place construct(JsonMap map) {
        Place place = new Place();
        place.name        = map.string("name");
        place.address     = map.string("formatted_address");
        place.vicinity    = map.string("vicinity");
        place.id          = map.string("place_id");
        place.reference   = map.string("reference");
        Geometry geometry = Geometry.of(map);
        place.latitude    = geometry.latitude;
        place.longitude   = geometry.longitude;
        place.open_now    = map.map("opening_hours").booleanValue("open_now");
        place.icon        = map.uri("icon");
        place.price_level = map.longValue("price_level");
        place.rating      = map.doubleValue("rating");
        place.types       = placeTypes(map);
        return place;
    }

    String[] placeTypes(JsonMap map) {
        //place.types       = map.list("types").toArray();
        return null;
    }
}
