package google;

import org.junit.Test;
import x.json.JsonMap;
import x.json.XJSONParser;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GoogleLocationTest {

    @Test
    public void can_parse_empty_map() throws Exception {
        GoogleLocation.Constructor parser = new GoogleLocation.Constructor();
        GoogleLocation location = parser.construct(parse("{}"));
        assertNotNull(location);
    }

    JsonMap sample() throws IOException {
        return parse(
        "{",
        "    'formatted_address' : '1600 Amphitheatre Parkway, Mountain View, CA 94043, USA',",
        "    'geometry' : {",
        "        'location' : {",
        "            'lat' : 37.4224764,",
        "            'lng' : -122.0842499",
        "         },",
        "         'location_type' : 'ROOFTOP',",
        "         'viewport' : {",
        "             'northeast' : {",
        "                 'lat' : 37.4238253802915,",
        "                 'lng' : -122.0829009197085",
        "              },",
        "             'southwest' : {",
        "                 'lat' : 37.4211274197085,",
        "                 'lng' : -122.0855988802915",
        "              }", // southwest
        "         }",      // viewport
        "    },",          // geometry
        "   'place_id' : 'ChIJ2eUgeAK6j4ARbn5u_wAGqWA',",
        "   'types' : [ 'street_address' ]",
        "}"
        );
    }

    @Test
    public void can_parse_sample() throws IOException {
        GoogleLocation.Constructor parser = new GoogleLocation.Constructor();
        GoogleLocation location = parser.construct(sample());
        assertNotNull(location);
        assertEquals("1600 Amphitheatre Parkway, Mountain View, CA 94043, USA",location.address);
        assertEquals("ROOFTOP",location.type);
        assertEquals(37.4224764,  location.latitude, 0.000001);
        assertEquals(-122.0842499,location.longitude,0.000001);
        assertEquals(1,location.types.length);
        assertEquals("street_address",location.types[0]);
    }

    private static JsonMap parse(String... lines) throws IOException {
        String json = JSON(lines);
        return (JsonMap) XJSONParser.parse(new StringReader(json));
    }

    static String JSON(String... lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line);
        }
        return out.toString().replaceAll("'", "\"");
    }
}