package oc2.screenfactories;

import java.util.List;
import oc1.services.Geocoder;
import oc1.uilist.StringToList;

/**
 *
 * @author Curt
 */
final class GeocoderStringToList
    implements StringToList
{

    public List listFor(String text) {
        return Geocoder.of().searchFor(text);
    }
   
}
