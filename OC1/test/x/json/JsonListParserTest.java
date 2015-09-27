package x.json;

import config.ShouldRun;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class JsonListParserTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void empty_json_array() throws IOException {
        List empty = parse("[]");
        assertTrue(empty instanceof List);
        assertEquals(0,empty.size());
        assertTrue(empty.isEmpty());
    }

    @Test
    public void a_string_in_a_list() throws IOException {
        assertEquals(list("barney"), parse("['barney']"));
    }

    @Test
    public void pair_of_strings_in_a_list() throws IOException {
        assertEquals(list("fred", "wilma"), parse("['fred','wilma']"));
    }

    @Test
    public void pair_of_strings_with_url() throws IOException {
        assertEquals(
           list(
                   "Vodka", "http://absolute/value_page",
                   "Spencer", "relative_value_page"
           ),
           parse(
               "[",
                   "'Vodka','http://absolute/value_page' , 'Spencer','relative_value_page' ",
               "]"
           )
        );
    }

    @Test
    public void multi_level_nested_lists() throws IOException {
        assertEquals(
          list("menu", list("id", "file", "value", "File")),
          parse(
              "[",
              "  'menu' , ['id' ,'file','value','File']",
              "]"
          )
        );
    }


    private static List list(Object... args) {
        return Arrays.asList(args);
    }

    private static List parse(String... lines) throws IOException {
        String json = JSON(lines);
        String[] tokens = XJSONParser.split(json);
        JsonListParser parser = new JsonListParser(tokens,0);
        return parser.parse();
    }

    private static String JSON(String... lines) {
        return XJSONParserTest.JSON(lines);
    }

}