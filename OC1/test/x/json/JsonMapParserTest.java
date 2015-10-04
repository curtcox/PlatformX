package x.json;

import config.ShouldRun;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

public class JsonMapParserTest {

    @Rule
    public Timeout globalTimeout = new Timeout(1000);

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void parse_throws_exception_when_first_token_is_not_left_curly_bracket() throws IOException {
        try {
            parse("}");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expected ({) as first token, but got (})",e.getMessage());
        }
    }

    @Test
    public void parse_throws_exception_when_comma_encounterd_with_no_key() throws IOException {
        try {
            parse("{ ,");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expected (key), but found (,)",e.getMessage());
        }
    }

    @Test
    public void parse_throws_exception_when_colon_encounterd_with_no_key() throws IOException {
        try {
            parse("{ :");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expected (key), but found (:)",e.getMessage());
        }
    }

    @Test
    public void parse_throws_exception_when_closing_curly_encounterd_with_no_value() throws IOException {
        try {
            parse("{ 'key' : }");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expected (value), but found (})",e.getMessage());
        }
    }

    @Test
    public void empty_json_map() throws IOException {
        assertEquals(new HashMap(),parse("{ }"));
    }

    @Test
    public void pair_of_strings_in_a_map() throws IOException {
        assertEquals(map("fred","wilma"), parse("{'fred':'wilma'}"));
    }

    @Test
    public void pair_of_strings_with_spaces_in_a_map() throws IOException {
        assertEquals(map("Mister","Mr."), parse("{ 'Mister' : 'Mr.' }"));
    }

    @Test
    public void pair_of_strings_with_spaces_in_a_list() throws IOException {
        assertEquals(map("Mister","Mr."), parse("{ 'Mister' : 'Mr.' }"));
    }

    @Test
    public void two_pairs_of_strings() throws IOException {
        assertEquals(map("fred","wilma","barney","betty"),
            parse("{'fred':'wilma','barney':'betty'}"));
    }

    @Test
    public void two_pairs_of_strings_with_spaces() throws IOException {
        assertEquals(map("bob","ted","sue","sally"),
                parse("{ 'bob' : 'ted' , 'sue' : 'sally' }"));
    }

    @Test
    public void pair_of_strings_with_url() throws IOException {
        assertEquals(
           map(
               "Vodka","http://absolute/value_page",
               "Spencer","relative_value_page"
           ),
           parse(
               "{",
               "'Vodka':'http://absolute/value_page' ,",
               "'Spencer':'relative_value_page' ",
               "}"
           )
        );
    }

    @Test
    public void multi_level_nested_maps() throws IOException {
        assertEquals(
          map("menu",map("id","file","value","File")),
          parse(
              "{",
              "  'menu' :{",
              "     'id': 'file',",
              "     'value': 'File'",
              "  }",
              "}"
          )
        );
    }

    @Test
    public void map_containing_a_list() throws IOException {
        assertEquals(
                map("menu",list("file")),
                parse("{ 'menu' : [ 'file' ] }"
                )
        );
    }

    @Test
    public void map_containing_just_a_list() throws IOException {
        assertEquals(
                map("stuff",list()),
                parse("{ 'stuff' : [ ] }"
                )
        );
    }

    @Test
    public void map_containing_list_of_maps() throws IOException {
        assertEquals(
                map("results",list(map("a","ape"),map("b","bee"))),
                parse("{ 'results' : [ { 'a' : 'ape'},{'b':'bee'} ] }"
                )
        );
    }

    @Test
    public void map_containing_list_of_empty_maps() throws IOException {
        assertEquals(
                map("results",list(map(),map())),
                parse("{ 'results' : [ { } , { } ] }"
                )
        );
    }
    @Test
    public void map_containing_map_of_maps() throws IOException {
        assertEquals(
                map("what",map("the", map("word", "bird"))),
                parse("{ 'what' : { 'the' : { 'word' : 'bird'} } }"
                )
        );
    }

    @Test
    public void map_containing_map_of_2_maps() throws IOException {
        assertEquals(
                map("what",map("the",map("word","bird") ,"make",map("plan","stan") )),
                parse("{ 'what' : { 'the' : { 'word' : 'bird'} ,  'make' : {'plan' : 'stan' }  } }"
                //     1          2         3                1             4                2  3 4
                )
        );
    }

    @Test
    public void end_is_index_of_next_token_after_parsing_empty_map() throws IOException {
        String[] tokens = XJSONParser.split("{},");
        JsonMapParser parser = new JsonMapParser(tokens,0);
        parser.parse();
        assertEquals(",",tokens[parser.end]);
    }

    @Test
    public void end_is_index_of_next_token_after_parsing_map_with_one_item() throws IOException {
        String[] tokens = XJSONParser.split("{'a':'ape'},");
        JsonMapParser parser = new JsonMapParser(tokens,0);
        parser.parse();
        assertEquals(",",tokens[parser.end]);
    }

    private static List list(Object... args) {
        return Arrays.asList(args);
    }

    private static Map map(Object...args) {
        return XJSONParserTest.map(args);
    }

    private static Map<String,Object> parse(String... lines) throws IOException {
        String json = JSON(lines);
        String[] tokens = XJSONParser.split(json);
        JsonMapParser parser = new JsonMapParser(tokens,0);
        return parser.parse();
    }

    private static String JSON(String... lines) {
        return XJSONParserTest.JSON(lines);
    }

}