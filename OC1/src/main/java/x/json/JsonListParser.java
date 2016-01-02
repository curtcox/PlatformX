package x.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class JsonListParser {

    final String[] tokens;
    final int start;
    int end;
    List<Json> list = new ArrayList<Json>();

    JsonListParser(String[] tokens, int start) {
        this.tokens = tokens;
        this.start = start;
    }

    JsonList parse() throws IOException {
        checkFirstToken();
        for (end=start + 1; end<tokens.length; end++) {
            String token = tokens[end];
            if (token.equals("[")) {
                JsonListParser parser = new JsonListParser(tokens,end);
                list.add(parser.parse());
                end = parser.end - 1;
                continue;
            }
            if (token.equals("{")) {
                JsonMapParser parser = new JsonMapParser(tokens,end);
                list.add(parser.parse());
                end = parser.end - 1;
                continue;
            }
            if (token.equals(",")) {
                continue;
            }
            if (token.equals("]")) {
                end++;
                return JsonList.of(list);
            }
            list.add(value(token));
        }
        return JsonList.of(list);
    }

    private void checkFirstToken() {
        String token = tokens[start];
        if (!token.equals("[")) {
            String message = "Expected [ as first token, but got " + token;
            throw new IllegalArgumentException(message);
        }
    }

    static Json value(String value) {
        return JsonValueParser.parse(value);
    }

}
