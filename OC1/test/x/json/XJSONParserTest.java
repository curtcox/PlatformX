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
    public void unquoted_returns_unquoted_string() {
        assertUnquotedEquals("", "");
        assertUnquotedEquals("a", "a");
        assertUnquotedEquals("word", "word");
        assertUnquotedEquals(quoted(""), "");
        assertUnquotedEquals(quoted("a"), "a");
        assertUnquotedEquals(quoted("word"), "word");
    }

    private void assertUnquotedEquals(String input, String output) {
        assertEquals(output,XJSONParser.unquoted(input));
    }

    private static List list(Object... args) {
        return Arrays.asList(args);
    }

    static Map map(Object...args) {
        Map map = new HashMap();
        for (int i=0; i<args.length; i++) {
            Object key = args[i];
            i++;
            Object value = args[i];
            map.put(key,value);
        }
        return map;
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