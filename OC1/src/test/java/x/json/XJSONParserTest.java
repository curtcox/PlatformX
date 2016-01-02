package x.json;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

public class XJSONParserTest {

    @Rule
    public Timeout globalTimeout = new Timeout(1000);

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void empty_json_map() throws IOException {
        assertEquals(new HashMap(),parse("{ }"));
    }

    @Test
    public void empty_json_array() throws IOException {
        assertEquals(new ArrayList(),parse("[]"));
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
    public void a_string_in_a_list() throws IOException {
        assertEquals(list("barney"), parse("['barney']"));
    }

    @Test
    public void a_string_with_an_escaped_embedded_quote_in_a_list() throws IOException {
        assertEquals(list("ba\"ney"), parse("['ba\\'ney']"));
    }

    @Test
    public void pair_of_strings_in_a_list() throws IOException {
        assertEquals(list("fred", "wilma"), parse("['fred','wilma']"));
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
    public void map_containing_lists_and_stuff() throws IOException {
        assertEquals(
                map("html_attributions",list(), "next_page_token","t","more",list()),
                parse("{ 'html_attributions' : [], 'next_page_token' : 't', 'more' : [] }"
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
    public void unquoted_returns_unquoted_string() {
        assertUnquotedEquals("", "");
        assertUnquotedEquals("a", "a");
        assertUnquotedEquals("word", "word");
        assertUnquotedEquals(quoted(""), "");
        assertUnquotedEquals(quoted("a"), "a");
        assertUnquotedEquals(quoted("word"), "word");
    }

    @Test
    public void split_of_empty_string_is_empty_list() {
        assertEquals(list(), split(""));
    }

    @Test
    public void split_returns_separator_tokens() {
        assertEquals(list("{","}","[","]",":",","), split("{}[]:,"));
        assertEquals(list("{","}","[","]",":",","), split(" { } [ ] : , "));
    }

    @Test
    public void split_returns_unquoted_words_without_separating_whitespace() {
        assertEquals(list("When","I","say","true","I","mean","it"), split("When I say true I mean it"));
    }

    @Test
    public void split_returns_quoted_words_with_internal_whitespace() {
        String input = "'So called''  ''white space'".replaceAll("'","\"");
        assertEquals(list("\"So called\"","\"  \"","\"white space\""), split(input));
    }

    private JsonList split(String string) {
        return JsonRenderer.list(Arrays.asList(XJSONParser.split(string)));
    }

    private void assertUnquotedEquals(String input, String output) {
        assertEquals(output,XJSONParser.unquoted(input));
    }

    static JsonList list(Object... args) {
        return JsonRenderer.list(args);
    }

    static Map map(Object...args) {
        Map map = new HashMap();
        for (int i=0; i<args.length; i++) {
            Object key = args[i];
            i++;
            Object value = args[i];
            map.put(key,value);
        }
        return JsonRenderer.map(map);
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

    static String quoted(String string) {
        return "\"" + string + "\"";
    }
}