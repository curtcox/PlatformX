package x.json;

import x.io.IO;
import x.parse.Lexer;
import x.parse.Tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class XJSONParser {

    final String[] tokens;
    final int start;

    private static final String[] separators = new String[] {
            "{","}", "[","]", ":",",","\""
    };

    private XJSONParser(String[] tokens, int start) {
        this.tokens = tokens;
        this.start = start;
    }

    public static Map<String, Object> parse(Reader reader) throws IOException {
        String[] tokens = tokens(reader);
        JsonMapParser parser = new JsonMapParser(tokens,0);
        return parser.parse();
    }

    static String unquoted(String value) {
        String trimmed = value.trim();
        return trimmed.substring(1,trimmed.length()-1);
    }

    private static String[] tokens(Reader reader) {
        return split(IO.stringOrEmptyFrom(reader));
    }

    static String[] split(String string) {
        return Lexer.transform(parts(string));
    }

    private static String[] parts(String string) {
        return Tokenizer.tokenize(string, separators);
    }

}
