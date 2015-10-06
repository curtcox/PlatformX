package x.json;

import config.ShouldRun;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

public class JsonListParserTest {

    @Rule
    public Timeout globalTimeout = new Timeout(1000);

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void parse_throws_exception_when_first_token_is_not_left_square_bracket() throws IOException {
        try {
            parse("]");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expected [ as first token, but got ]", e.getMessage());
        }
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


    @Test
    public void list_that_contains_a_map() throws IOException {
        assertEquals(
                list("menu", map("id", "file", "value", "File")),
                parse(
                        "[",
                        "  'menu' , {'id' : 'file','value' : 'File'}",
                        "]"
                )
        );
    }

    @Test
    public void list_containing_just_a_map() throws IOException {
        assertEquals(
                list(map()),
                parse("[ { } ]"
                )
        );
    }

    @Test
    public void list_of_empty_maps() throws IOException {
        assertEquals(
                list(map(),map()),
                parse("[ {}, {} ]"
                )
        );
    }

    @Test
    public void two_level_growing_nested_empty_lists() throws IOException {
        assertEquals(
                list( list(), list() ),
                parse("[ [] , [] ]"
                )
        );
    }

    @Test
    public void three_level_growing_nested_empty_lists() throws IOException {
        assertEquals(
                list(  list(list(), list(), list()),  list(list(), list(), list())  ),
                parse("[   [ [],[],[] ]  ,  [ [],[],[] ]   ]"
                )
        );
    }

    @Test
    public void three_level_growing_nested_lists() throws IOException {
        assertEquals(
                list(  list(list(1L), list(2L), list(3L)),  list(list(4L), list(5L), list(6L))  ),
                parse("[   [ [1],[2],[3] ]  ,  [ [4],[5],[6] ]   ]"
                )
        );
    }

    @Test
    public void list_of_maps() throws IOException {
        assertEquals(
                list(map("a","ape"),map("b","bee")),
                parse("[ { 'a' : 'ape'},{'b':'bee'} ]"
                )
        );
    }

    @Test
    public void end_is_index_of_next_token_after_parsing_empty_list() throws IOException {
        assertEquals(",",after("[] ,"));
        assertEquals("{",after("[] {"));
    }

    @Test
    public void end_is_index_of_next_token_after_parsing_list_with_one_item() throws IOException {
        assertEquals(",",after("['a'] ,"));
        assertEquals("}",after("['a'] }"));
    }

    @Test
    public void end_is_index_of_next_token_after_parsing_nested_lists() throws IOException {
        assertEquals(",",after("[ [] ] ,"));
        assertEquals("}",after("[ [] ] }"));
        assertEquals(",",after("[ [[]] ] ,"));
        assertEquals("}",after("[ [[]] ] }"));
    }

    private String after(String json) throws IOException {
        String[] tokens = XJSONParser.split(json);
        JsonListParser parser = new JsonListParser(tokens,0);
        parser.parse();
        return tokens[parser.end];
    }
    private static List list(Object... args) {
        return Arrays.asList(args);
    }

    private static Map map(Object...args) {
        return XJSONParserTest.map(args);
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