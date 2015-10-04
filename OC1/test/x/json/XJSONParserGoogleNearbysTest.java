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
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class XJSONParserGoogleNearbysTest {

    @Rule
    public Timeout globalTimeout = new Timeout(1000);

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }


    Object sample2() throws IOException {
        return parse(
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
        Map map = (Map) sample2();
        assertEquals(3, map.size());
        assertTrue(map.get("html_attributions") instanceof List);
        assertEquals("CoQC8QAAAMWFIlUzCyyxsw",map.get("next_page_token"));
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

    Object sample3() throws IOException {
        return parse(
                "{ 'results' : [ { } , { } ]  }");
    }

    @Test
    public void sample3_results() throws IOException {
        Map map = (Map) sample3();
        assertEquals(1, map.size());
        List results = (List) map.get("results");
        assertTrue(results instanceof List);
        assertEquals(2,results.size());
        Map result1 = (Map) results.get(0);
        assertEquals(0,result1.size());
        Map result2 = (Map) results.get(1);
        assertEquals(0,result2.size());
    }

    Object sample4() throws IOException {
        return parse(
                "{",
                "   'results' : [",
                "       {",
                "           'geometry' : {",
                "               'location' : {",
                "                }",
                "            }",
                "        }",
                "    ]",
                "}");
    }

    @Test
    public void sample4_response() throws IOException {
        Map map = (Map) sample4();
        assertEquals(1, map.size());
        assertTrue(map.get("results") instanceof List);
    }

    Object sample1() throws IOException {
        return parse(
           "{",
           //"   'html_attributions' : [],",
           //"   'next_page_token' : 'CoQC8QAAAMWFIlUzCyyxsw',",
           "   'results' : [",
           "       {",
           "           'geometry' : {",
           "               'location' : {",
           //"                   'lat' : 38.652775,",
           //"                   'lng' : -90.339675",
           "                }",
           "            },",
           //"           'icon' : 'https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png',",
           //"           'id' : '229fbe2845c3bc1937f799aa2a48481ba1111b8d',",
           //"           'name' : 'First Banks Inc',",
           //"           'opening_hours' : {",
           //"                 'open_now' : false,",
           //"                 'weekday_text' : []",
           //"            },",
           //"            'place_id' : 'ChIJ3XZe1NQ034cRWbeNNRW9hDM',",
           //"            'reference' : 'CnRiAAlT3oghT7cE',",
           //"            'scope' : 'GOOGLE',",
           //"            'types' : [ 'bank', 'atm', 'finance', 'point_of_interest', 'establishment' ],",
           //"            'vicinity' : '135 North Meramec Avenue, Clayton'",
           "        },", // result 1
           "        {",
//           "             'geometry' : {",
//           "                'location' : {",
//           "                    'lat' : 38.651613,",
//           "                    'lng' : -90.34013",
//           "                 }",
//           "              },",
//           "             'icon' : 'https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png',",
//           "             'id' : '247b81c39ff3f929a9e1b6ffbd501fc9ae177950',",
//           "        'name' : 'Business Bank of St Louis',",
//           "        'place_id' : 'ChIJmSGSJivL2IcRzhKez9DcZrE',",
//           "        'reference' : 'CnRtAAAAkSnopbmEo5GVZnrLFcU',",
//           "        'scope' : 'GOOGLE',",
//           "        'types' : [ 'bank', 'atm', 'finance', 'point_of_interest', 'establishment' ],",
//           "        'vicinity' : '8000 Maryland Ave, Saint Louis'",
           "    }",
           "]",
           "}");
    }

    @Test
    public void sample_response_top_level() throws IOException {
        Map map = (Map) sample1();
        assertEquals(3, map.size());
        assertTrue(map.get("html_attributions") instanceof List);
        assertTrue(map.get("results") instanceof List);
        assertEquals("CoQC8QAAAMWFIlUzCyyxsw",map.get("next_page_token"));
    }

    @Test
    public void sample_response_html_attributions() throws IOException {
        Map map = (Map) sample1();
        List list = (List) map.get("html_attributions");
        assertEquals(0, list.size());
    }

    @Test
    public void sample_response_results() throws IOException {
        Map map = (Map) sample1();
        List list = (List) map.get("results");
        assertEquals(2, list.size());
    }

    @Test
    public void sample_response_results_0() throws IOException {
        Map map = (Map) sample1();
        List list = (List) map.get("results");
        Map result0 = (Map) list.get(0);
        assertEquals(2, result0.size());
    }

    private static Object parse(String... lines) throws IOException {
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