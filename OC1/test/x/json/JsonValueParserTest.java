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

    private void assertParseEquals(String input, String output) {
        assertEquals(output,JsonValueParser.parse(input));
    }

    private void assertParseEquals(String input, Double output) {
        assertEquals(output,JsonValueParser.parse(input));
    }

    static String quoted(String string) {
        return "\"" + string + "\"";
    }
}