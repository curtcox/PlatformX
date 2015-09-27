package x.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class JsonListParser {

    final String[] tokens;
    final int start;
    int end;

    JsonListParser(String[] tokens, int start) {
        this.tokens = tokens;
        this.start = start;
    }

    List parse() throws IOException {
        List list = new ArrayList();
        for (int i=start; i<tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("[") && list.isEmpty()) {
                continue;
            }
            if (token.equals("[")) {
                JsonListParser parser = new JsonListParser(tokens,i + 1);
                list.add(parser.parse());
                i = parser.end;
                continue;
            }
            if (token.equals(",")) {
                continue;
            }
            if (token.equals("]")) {
                end = i;
                return list;
            }
            list.add(unquoted(token));
        }
        return list;
    }

    static String unquoted(String value) {
        return XJSONParser.unquoted(value);
    }

}
