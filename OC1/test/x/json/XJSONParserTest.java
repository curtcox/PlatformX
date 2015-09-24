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
    public void two_pairs_of_strings() throws IOException {
        assertEquals(map("fred","wilma","barney","betty"),
            parse("{'fred':'wilma','barney':'betty'}"));
    }

    private static Map map(String...args) {
        Map map = new HashMap();
        for (int i=0; i<args.length; i++) {
            String key = args[i];
            i++;
            String value = args[i];
            map.put(key,value);
        }
        return map;
    }

    private static Map<String,Object> parse(String... lines) throws IOException {
        XJSONParser parser = new XJSONParser();
        String json = JSON(lines);
        return parser.parseJSON(new StringReader(json));
    }

    private static String JSON(String... lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line);
        }
        return out.toString().replaceAll("'", "\"");
    }

}