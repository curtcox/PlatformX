package x.pagefactories;

import java.util.List;

import x.Registry;
import x.services.IGeocoder;
import x.uilist.StringToList;

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
