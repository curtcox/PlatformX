package x.json;

import config.ShouldRun;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
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

     Object sample() throws IOException {
           return parse(
           "{",
               "'html_attributions' : [],",
               "'next_page_token' : 'CoQC8QAAAMWFIlUzCyyxsw',",
               "'results' : [",
               "    {",
               "'       geometry' : {",
               "'           location' : {",
               "'               lat' : 38.652775,",
               "'               lng' : -90.339675",
               "             }",
               "        },",
               "     'icon' : 'https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png',",
               "     'id' : '229fbe2845c3bc1937f799aa2a48481ba1111b8d',",
               "     'name' : 'First Banks Inc',",
               "     'opening_hours' : {",
               "         'open_now' : false,",
               "         'weekday_text' : []",
               "      },",
               "'     place_id' : 'ChIJ3XZe1NQ034cRWbeNNRW9hDM',",
               "'     reference' : 'CnRiAAlT3oghT7cE',",
               "'     scope' : 'GOOGLE',",
               "      'types' : [ 'bank', 'atm', 'finance', 'point_of_interest', 'establishment' ],",
               "      'vicinity' : '135 North Meramec Avenue, Clayton'",
               "    },", // result 1
               "    {",
               "        'geometry' : {",
               "            'location' : {",
               "                'lat' : 38.651613,",
               "                'lng' : -90.34013",
               "             }",
               "         },",
               "        'icon' : 'https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png',",
               "        'id' : '247b81c39ff3f929a9e1b6ffbd501fc9ae177950',",
               "        'name' : 'Business Bank of St Louis',",
               "        'place_id' : 'ChIJmSGSJivL2IcRzhKez9DcZrE',",
               "        'reference' : 'CnRtAAAAkSnopbmEo5GVZnrLFcU',",
               "        'scope' : 'GOOGLE',",
               "        'types' : [ 'bank', 'atm', 'finance', 'point_of_interest', 'establishment' ],",
               "        'vicinity' : '8000 Maryland Ave, Saint Louis'",
               "    }",
               "]",
         "}");
    }

    @Test
    public void sample_response_top_level() throws IOException {
        Map map = (Map) sample();
        assertEquals(3, map.size());
        assertTrue(map.get("html_attributions") instanceof List);
        assertTrue(map.get("results") instanceof List);
        assertEquals("CoQC8QAAAMWFIlUzCyyxsw",map.get("next_page_token"));
    }

    @Test
    public void sample_response_html_attributions() throws IOException {
        Map map = (Map) sample();
        List list = (List) map.get("html_attributions");
        assertEquals(0, list.size());
    }

    @Test
    public void sample_response_results() throws IOException {
        Map map = (Map) sample();
        List list = (List) map.get("results");
        assertEquals(2, list.size());
    }

    @Test
    public void sample_response_results_0() throws IOException {
        Map map = (Map) sample();
        List list = (List) map.get("results");
        Map result = (Map) list.get(0);
        assertEquals(10, result.size());
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