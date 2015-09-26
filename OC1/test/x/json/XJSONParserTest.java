package x.json;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

public class XJSONParserTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void empty_json() throws IOException {
        assertEquals(new HashMap(),parse("{ }"));
    }

    @Test
    public void pair_of_strings() throws IOException {
        assertEquals(map("fred","wilma"), parse("{'fred':'wilma'}"));
    }

    @Test
    public void pair_of_strings_with_spaces() throws IOException {
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
    public void multi_level_nested() throws IOException {
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

    private static Map map(Object...args) {
        Map map = new HashMap();
        for (int i=0; i<args.length; i++) {
            Object key = args[i];
            i++;
            Object value = args[i];
            map.put(key,value);
        }
        return map;
    }

    private static Map<String,Object> parse(String... lines) throws IOException {
        String json = JSON(lines);
        return XJSONParser.parse(new StringReader(json));
    }

    private static String JSON(String... lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line);
        }
        return out.toString().replaceAll("'", "\"");
    }

}