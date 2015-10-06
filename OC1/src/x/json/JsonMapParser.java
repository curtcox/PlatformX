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
        for (end=start + 1; end<tokens.length; end++) {
            String token = tokens[end];
            if (token.equals("{")) {
                JsonMapParser parser = new JsonMapParser(tokens,end);
                put(parser.parse());
                end = parser.end - 1;
                key = null;
                continue;
            }
            if (token.equals(":")) {
                key = (String) value;
                value = null;
                checkKeySet();
                continue;
            }
            if (token.equals(",")) {
                if (!map.isEmpty()) {
                    continue;
                }
                put(value(value));
                continue;
            }
            if (token.equals("}")) {
                if (key!=null) {
                    checkValueSet();
                    put(value(value));
                }
                end++;
                return map;
            }
            if (token.equals("[")) {
                JsonListParser parser = new JsonListParser(tokens, end);
                put(parser.parse());
                end = parser.end - 1;
                continue;
            }
            value = token;
        }
        throw new IllegalArgumentException("No closing (}) found");
    }

    private Object value(Object object) {
        checkKeySet();
        return valueOf((String) object);
    }

    private void checkKeySet() {
        if (key==null) {
            String message = "Expected (key), but found (" + tokens[end] + ") after (" + tokens[end - 1] + ")";
            throw new IllegalArgumentException(message);
        }
    }

    private void put(Object value) {
        map.put(unquoted(key),value);
        key = null;
        this.value = null;
    }

    private void checkValueSet() {
        if (value==null) {
            String message = "Expected (value), but found (" + tokens[end] + ") after (" + tokens[end - 1] + ")";
            throw new IllegalArgumentException(message);
        }
    }

    private void checkFirstToken() {
        String token = tokens[start];
        if (!token.equals("{")) {
            String message = "Expected ({) as first token, but got (" + token + ")";
            throw new IllegalArgumentException(message);
        }
    }

    static String unquoted(String value) {
        return XJSONParser.unquoted(value);
    }

    static Object valueOf(String value) {
        return JsonValueParser.parse(value);
    }
}
