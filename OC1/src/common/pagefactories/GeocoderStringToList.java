package common.pagefactories;

import java.util.List;

import common.Registry;
import common.services.IGeocoder;
import common.uilist.StringToList;

final class GeocoderStringToList
    implements StringToList
{

    public List listFor(String text) {
        return geocoder().searchFor(text);
    }

    static IGeocoder geocoder() {
        return Registry.get(IGeocoder.class);
    }
}
