package x.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

final class JsonMapParser {

    final String[] tokens;
    final int start;
    int end;

    JsonMapParser(String[] tokens, int start) {
        this.tokens = tokens;
        this.start = start;
    }

    Map<String, Object> parse() throws IOException {
        Map map = new HashMap<String, Object>();
        String key = null;
        Object value = null;
        for (int i=start; i<tokens.length; i++) {
            String token = tokens[i];
            if (key !=null && token.equals("{")) {
                JsonMapParser parser = new JsonMapParser(tokens,i + 1);
                map.put(unquoted(key),parser.parse());
                key = null;
                i = parser.end + 1;
            }
            if (key == null && token.equals("}")) {
                end = i;
                return map;
            }
            if (token.equals(":")) {
                key = (String) value;
            }
            if (token.equals("}") || token.equals(",")) {
                if (key!=null) {
                    map.put(unquoted(key), unquoted((String)value));
                    key = null;
                }
            }
            if (token.equals("[")) {
                JsonListParser parser = new JsonListParser(tokens, i + 1);
                map.put(unquoted(key),parser.parse());
                i = parser.end + 1;
            } else {
                value = token;
            }
        }
        return map;
    }

    static String unquoted(String value) {
        return XJSONParser.unquoted(value);
    }

}
