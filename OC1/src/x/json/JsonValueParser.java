package x.json;

final class JsonValueParser {
    static Object parse(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            // It wasn't a long
        }
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return XJSONParser.unquoted(input);
        }
    }
}
