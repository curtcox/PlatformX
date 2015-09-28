package x.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class JsonListParser {

    final String[] tokens;
    final int start;
    int end;
    List list = new ArrayList();

    JsonListParser(String[] tokens, int start) {
        this.tokens = tokens;
        this.start = start;
    }

    List parse() throws IOException {
        for (end=start; end<tokens.length; end++) {
            String token = tokens[end];
            if (token.equals("[") && list.isEmpty()) {
                continue;
            }
            if (token.equals("[")) {
                JsonListParser parser = new JsonListParser(tokens,end + 1);
                list.add(parser.parse());
                end = parser.end;
                continue;
            }
            if (token.equals("{")) {
                JsonMapParser parser = new JsonMapParser(tokens,end + 1);
                list.add(parser.parse());
                end = parser.end;
                continue;
            }
            if (token.equals(",")) {
                continue;
            }
            if (token.equals("]")) {
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
