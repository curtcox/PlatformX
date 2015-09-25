package x.json;

import x.io.IO;
import x.util.Tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public final class XJSONParser {

    final String[] tokens;
    final int start;
    int end;

    private static final String[] separators = new String[] {
            ":",",","{","}"
    };

    private XJSONParser(String[] tokens, int start) {
        this.tokens = tokens;
        this.start = start;
    }

    public static Map<String, Object> parse(Reader reader) throws IOException {
        String[] tokens = tokens(reader);
        XJSONParser parser = new XJSONParser(tokens,0);
        return parser.parse();
    }

    private Map<String, Object> parse() throws IOException {
        Map map = new HashMap<String, Object>();
        String key = null;
        String value = null;
        for (int i=start; i<tokens.length; i++) {
            String token = tokens[i];
            if (key !=null && token.equals("{")) {
                XJSONParser parser = new XJSONParser(tokens,i + 1);
                map.put(unquoted(key),parser.parse());
                key = null;
                i = parser.end + 1;
            }
            if (key == null && token.equals("}")) {
                end = i;
                return map;
            }
            if (token.equals(":")) {
                key = value;
            }
            if (token.equals("}") || token.equals(",")) {
                if (key!=null) {
                    map.put(unquoted(key), unquoted(value));
                    key = null;
                }
            }
            value = token;
        }
        return map;
    }

    private String unquoted(String value) {
        String trimmed = value.trim();
        return trimmed.substring(1,trimmed.length()-1);
    }

    private static String[] tokens(Reader reader) {
        return Tokenizer.tokenize(IO.stringOrEmptyFrom(reader),separators);
    }

}
