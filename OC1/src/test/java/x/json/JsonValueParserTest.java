package x.json;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonValueParserTest {

    @Test
    public void parse_returns_unquoted_string_when_string() {
        assertParseEquals("", "");
        assertParseEquals("a", "a");
        assertParseEquals("word", "word");
        assertParseEquals(quoted(""), "");
        assertParseEquals(quoted("a"), "a");
        assertParseEquals(quoted("word"), "word");
    }

    @Test
    public void parse_returns_double_when_double() {
        assertParseEquals("1.0", 1.0);
        assertParseEquals("3.14", 3.14);
    }

    @Test
    public void parse_returns_long_when_integer() {
        assertParseEquals("1", 1L);
        assertParseEquals("3", 3L);
    }

    @Test
    public void parse_returns_boolean_when_boolean() {
        assertParseEquals("true",true);
        assertParseEquals("false", false);
    }

    private void assertParseEquals(String input, String output) {
        JsonValue json = JsonValueParser.parse(input);
        assertEquals(output,json.toString());
    }

    private void assertParseEquals(String input, Double output) {
        JsonValue json = JsonValueParser.parse(input);
        assertEquals(output,json.doubleValue());
    }

    private void assertParseEquals(String input, Long output) {
        JsonValue json = JsonValueParser.parse(input);
        assertEquals(output,json.longValue());
    }

    private void assertParseEquals(String input, Boolean output) {
        JsonValue json = JsonValueParser.parse(input);
        assertEquals(output,json.booleanValue());
    }

    static String quoted(String string) {
        return "\"" + string + "\"";
    }
}