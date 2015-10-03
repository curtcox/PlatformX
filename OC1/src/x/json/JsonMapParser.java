package x.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

final class JsonMapParser {

    final String[] tokens;
    final int start;
    int end;
    Map map = new HashMap<String, Object>();
    String key = null;
    Object value = null;

    JsonMapParser(String[] tokens, int start) {
        this.tokens = tokens;
        this.start = start;
    }

    Map<String, Object> parse() throws IOException {
        checkFirstToken();
        for (end=start; end<tokens.length; end++) {
            String token = tokens[end];
            if (key !=null && token.equals("{")) {
                JsonMapParser parser = new JsonMapParser(tokens,end);
                map.put(unquoted(key),parser.parse());
                key = null;
                end = parser.end + 1;
            }
            if (key == null && token.equals("}")) {
                end++;
                return map;
            }
            if (token.equals(":")) {
                key = (String) value;
            }
            if (token.equals("}") || token.equals(",")) {
                if (key!=null) {
                    map.put(unquoted(key), value((String)value));
                    key = null;
                }
            }
            if (key == null && token.equals("}")) {
                end++;
                return map;
            }
            if (token.equals("[")) {
                JsonListParser parser = new JsonListParser(tokens, end);
                map.put(unquoted(key),parser.parse());
                end = parser.end + 1;
            } else {
                value = token;
            }
        }
        return map;
    }

    private void checkFirstToken() {
        String token = tokens[start];
        if (!token.equals("{")) {
            String message = "Expected { as first token, but got " + token;
            throw new IllegalArgumentException(message);
        }
    }

    static String unquoted(String value) {
        return XJSONParser.unquoted(value);
    }

    static Object value(String value) {
        return JsonValueParser.parse(value);
    }
}
