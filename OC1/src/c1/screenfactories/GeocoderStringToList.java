package c1.screenfactories;

import java.util.List;
import c1.services.Geocoder;
import c1.uilist.StringToList;

final class GeocoderStringToList
    implements StringToList
{

    public List listFor(String text) {
        return Geocoder.of().searchFor(text);
    }
   
}
