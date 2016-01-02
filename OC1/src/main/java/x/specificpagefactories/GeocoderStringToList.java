package x.specificpagefactories;

import x.app.Registry;
import x.services.XGeocoder;
import x.uilist.StringToList;

import java.util.List;

final class GeocoderStringToList
    implements StringToList
{

    public List listFor(String text) {
        return geocoder().searchFor(text);
    }

    static XGeocoder geocoder() {
        return Registry.get(XGeocoder.class);
    }
}
