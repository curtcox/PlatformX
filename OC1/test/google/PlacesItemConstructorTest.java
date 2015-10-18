package google;

import org.junit.Test;
import x.json.Json;
import x.json.JsonMap;
import x.json.XJSONParser;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class PlacesItemConstructorTest {

    JsonMap sample() throws IOException {
        return (JsonMap) parse(
        "{",
        "    'geometry' : {",
        "        'location' : {",
        "            'lat' : 38.652775,",
        "            'lng' : -90.339675",
        "         }",
        "     },",
        "    'icon' : 'https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png',",
        "    'id' : '229fbe2845c3bc1937f799aa2a48481ba1111b8d',",
        "    'name' : 'First Banks Inc',",
        "    'opening_hours' : {",
        "        'open_now' : false,",
        "        'weekday_text' : []",
        "    },",
        "    'place_id' : 'ChIJ3XZe1NQ034cRWbeNNRW9hDM',",
        "    'reference' : 'CnRiAAlT3oghT7cE',",
        "    'scope' : 'GOOGLE',",
        "    'types' : [ 'bank', 'atm', 'finance', 'point_of_interest', 'establishment' ],",
        "    'vicinity' : '135 North Meramec Avenue, Clayton'",
        "}"
        );
    }

    @Test
    public void can_parse_sample() throws Exception {
        PlacesItemConstructor parser = new PlacesItemConstructor();
        Place place = parser.construct(sample());
        assertNotNull(place);
        assertEquals("ChIJ3XZe1NQ034cRWbeNNRW9hDM",place.id);
        assertEquals("First Banks Inc",place.name);
        assertEquals("CnRiAAlT3oghT7cE",place.reference);
        assertEquals("135 North Meramec Avenue, Clayton",place.vicinity);
        assertEquals(
            new URI("https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png"),
            place.icon);
        assertEquals(
            Arrays.asList("bank", "atm", "finance", "point_of_interest", "establishment"),
            Arrays.asList(place.types));
        assertFalse(place.open_now);
        assertEquals(38.652775, place.latitude, 0.00001);
        assertEquals(-90.339675,place.longitude,0.00001);
    }

    private static Json parse(String... lines) throws IOException {
        String json = JSON(lines);
        return XJSONParser.parse(new StringReader(json));
    }

    static String JSON(String... lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line);
        }
        return out.toString().replaceAll("'", "\"");
    }

}