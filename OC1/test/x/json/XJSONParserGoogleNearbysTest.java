package x.json;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class XJSONParserGoogleNearbysTest {

    @Rule
    public Timeout globalTimeout = new Timeout(1000);

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }


    JsonMap sample2() throws IOException {
        return (JsonMap) parse(
                "{",
                "   'html_attributions' : [],",
                "   'next_page_token' : 'CoQC8QAAAMWFIlUzCyyxsw',",
                "   'results' : [",
                "    {",
                "       'geometry' : {",
                "           'location' : { }",
                "        }",
                "    }",
                "]",
                "}");
    }

    @Test
    public void sample2_response_top_level() throws IOException {
        JsonMap map = sample2();
        assertEquals(3, map.size());
        assertTrue(map.get("html_attributions") instanceof List);
        assertJsonStringEquals("CoQC8QAAAMWFIlUzCyyxsw", map.get("next_page_token"));
        List results = (List) map.get("results");
        assertTrue(results instanceof List);
        assertEquals(1,results.size());
        Map result = (Map) results.get(0);
        assertEquals(1,result.size());
        Map geometry = (Map) result.get("geometry");
        assertEquals(1,geometry.size());
        Map location = (Map) geometry.get("location");
        assertEquals(0,location.size());
    }

    JsonMap sample3() throws IOException {
        return (JsonMap) parse(
                "{ 'results' : [ { } , { } ]  }");
    }

    @Test
    public void sample3_results() throws IOException {
        JsonMap map = sample3();
        assertEquals(1, map.size());
        List results = (List) map.get("results");
        assertTrue(results instanceof List);
        assertEquals(2,results.size());
        Map result1 = (Map) results.get(0);
        assertEquals(0,result1.size());
        Map result2 = (Map) results.get(1);
        assertEquals(0,result2.size());
    }

    JsonMap sample4() throws IOException {
        return (JsonMap) parse(
                "{",
                "   'results' : [",
                "       {",
                "           'geometry' : {",
                "               'location' : { }",
                "            },",
                "            'icon' : 'cu'",
                "       }",
                "    ]",
                "}");
    }

    @Test
    public void sample4_response() throws IOException {
        JsonMap map = sample4();
        assertEquals(1, map.size());
        assertTrue(map.get("results") instanceof List);
    }

    JsonMap sample1() throws IOException {
        return (JsonMap) parse(
           "{",
           "   'html_attributions' : [],",
           "   'next_page_token' : 'CoQC8QAAAMWFIlUzCyyxsw',",
           "   'results' : [",
           "       {",
           "           'geometry' : {",
           "               'location' : {",
           "                   'lat' : 38.652775,",
           "                   'lng' : -90.339675",
           "                }",
           "            },",
           "           'icon' : 'https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png',",
           "           'id' : '229fbe2845c3bc1937f799aa2a48481ba1111b8d',",
           "           'name' : 'First Banks Inc',",
           "           'opening_hours' : {",
           "                 'open_now' : false,",
           "                 'weekday_text' : []",
           "            },",
           "            'place_id' : 'ChIJ3XZe1NQ034cRWbeNNRW9hDM',",
           "            'reference' : 'CnRiAAlT3oghT7cE',",
           "            'scope' : 'GOOGLE',",
           "            'types' : [ 'bank', 'atm', 'finance', 'point_of_interest', 'establishment' ],",
           "            'vicinity' : '135 North Meramec Avenue, Clayton'",
           "        },", // result 1
           "        {",
           "             'geometry' : {",
           "                'location' : {",
           "                    'lat' : 38.651613,",
           "                    'lng' : -90.34013",
           "                 }",
           "              },",
           "             'icon' : 'https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png',",
           "             'id' : '247b81c39ff3f929a9e1b6ffbd501fc9ae177950',",
           "             'name' : 'Business Bank of St Louis',",
           "             'place_id' : 'ChIJmSGSJivL2IcRzhKez9DcZrE',",
           "             'reference' : 'CnRtAAAAkSnopbmEo5GVZnrLFcU',",
           "             'scope' : 'GOOGLE',",
           "             'types' : [ 'bank', 'atm', 'finance', 'point_of_interest', 'establishment' ],",
           "             'vicinity' : '8000 Maryland Ave, Saint Louis'",
           "        }",
           "    ]",
           "}");
    }

    @Test
    public void sample_response_top_level() throws IOException {
        JsonMap map = sample1();
        assertEquals(3, map.size());
        assertTrue(map.get("html_attributions") instanceof List);
        assertTrue(map.get("results") instanceof List);
        assertJsonStringEquals("CoQC8QAAAMWFIlUzCyyxsw", map.get("next_page_token"));
    }

    @Test
    public void sample_response_html_attributions() throws IOException {
        JsonMap map = sample1();
        List list = (List) map.get("html_attributions");
        assertEquals(0, list.size());
    }

    @Test
    public void sample_response_results() throws IOException {
        JsonMap map = sample1();
        List list = (List) map.get("results");
        assertEquals(2, list.size());
    }

    @Test
    public void sample_response_opening_hours() throws IOException {
        JsonMap map = sample1();
        JsonList list = (JsonList) map.get("results");
        JsonMap result = (JsonMap) list.get(0);
        JsonMap opening_hours = (JsonMap) result.get("opening_hours");
        assertEquals(2, opening_hours.size());
        assertJsonFalse(opening_hours.get("open_now"));
        assertTrue(opening_hours.get("weekday_text") instanceof List);
    }

    @Test
    public void sample_response_results_0() throws IOException {
        JsonMap map = sample1();
        JsonList list = (JsonList) map.get("results");
        JsonMap result0 = (JsonMap) list.get(0);
        assertEquals(10, result0.size());
        assertJsonStringEquals("229fbe2845c3bc1937f799aa2a48481ba1111b8d", result0.get("id"));
        assertJsonStringEquals("First Banks Inc", result0.get("name"));
        assertJsonStringEquals("ChIJ3XZe1NQ034cRWbeNNRW9hDM", result0.get("place_id"));
        assertJsonStringEquals("CnRiAAlT3oghT7cE", result0.get("reference"));
        assertJsonStringEquals("GOOGLE", result0.get("scope"));
        assertJsonStringEquals("135 North Meramec Avenue, Clayton", result0.get("vicinity"));
        assertJsonStringEquals("https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png", result0.get("icon"));
    }

    @Test
    public void sample_response_results_1() throws IOException {
        JsonMap map = sample1();
        JsonList list = (JsonList) map.get("results");
        JsonMap result1 = (JsonMap) list.get(1);
        assertEquals(9, result1.size());
        assertJsonStringEquals("247b81c39ff3f929a9e1b6ffbd501fc9ae177950", result1.get("id"));
        assertJsonStringEquals("Business Bank of St Louis", result1.get("name"));
        assertJsonStringEquals("ChIJmSGSJivL2IcRzhKez9DcZrE", result1.get("place_id"));
        assertJsonStringEquals("CnRtAAAAkSnopbmEo5GVZnrLFcU", result1.get("reference"));
        assertJsonStringEquals("GOOGLE", result1.get("scope"));
        assertJsonStringEquals("8000 Maryland Ave, Saint Louis", result1.get("vicinity"));
        assertJsonStringEquals("https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png", result1.get("icon"));
    }

    @Test
    public void sample_response_results_geometry() throws IOException {
        JsonMap map = sample1();
        JsonList list = (JsonList) map.get("results");
        JsonMap result = (JsonMap) list.get(1);
        JsonMap geometry = (JsonMap) result.get("geometry");
        assertEquals(1, geometry.size());
        JsonMap location = (JsonMap) geometry.get("location");
        assertJsonEquals(38.651613, location.get("lat"));
        assertJsonEquals(-90.34013, location.get("lng"));
    }

    @Test
    public void sample_response_results_types() throws IOException {
        JsonMap map = sample1();
        JsonList list = (JsonList) map.get("results");
        JsonMap result = (JsonMap) list.get(1);
        JsonList types = (JsonList) result.get("types");
        assertEquals(5,types.size());
        assertJsonStringEquals("bank", types.get(0));
        assertJsonStringEquals("atm", types.get(1));
        assertJsonStringEquals("finance", types.get(2));
        assertJsonStringEquals("point_of_interest", types.get(3));
        assertJsonStringEquals("establishment", types.get(4));
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

    private void assertJsonStringEquals(String string, Json json) {
        JsonValue value = (JsonValue) json;
        assertEquals(string,value.toString());
    }

    private void assertJsonFalse(Json json) {
        JsonValue value = (JsonValue) json;
        assertFalse(value.booleanValue());
    }

    private void assertJsonEquals(double doubleValue, Json json) {
        JsonValue value = (JsonValue) json;
        assertEquals("=", doubleValue, value.doubleValue(), 0.000001);
    }

}