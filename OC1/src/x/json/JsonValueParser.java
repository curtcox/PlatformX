package x.json;

final class JsonValueParser {
    public static Object parse(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return XJSONParser.unquoted(input);
        }
    }
}
