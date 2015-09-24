package x.json;

import x.io.IO;
import x.util.Tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public final class XJSONParser {
    private static final String[] separators = new String[] {
            ":",",","{","}"
    };

    public Map<String, Object> parseJSON(Reader reader) throws IOException {
        Map map = new HashMap<String, Object>();
        String key = null;
        String value = null;
        for (String token : tokens(reader)) {
            if (token.equals(":")) {
                key = value;
            }
            if (token.equals("}") || token.equals(",")) {
                if (key!=null) {
                    map.put(unquoted(key), unquoted(value));
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

    private String[] tokens(Reader reader) {
        return Tokenizer.tokenize(IO.stringOrEmptyFrom(reader),separators);
    }

}
