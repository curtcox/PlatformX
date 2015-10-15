package x.json;

final class JsonValueParser {
    static JsonValue parse(String input) {
        try {
            return JsonValue.of(input,Long.parseLong(input));
        } catch (NumberFormatException e) {
            // It wasn't a long
        }
        if (input.equals("true")) {
            return JsonValue.of(input,true);
        }
        if (input.equals("false")) {
            return JsonValue.of(input,false);
        }
        try {
            return JsonValue.of(input,Double.parseDouble(input));
        } catch (NumberFormatException e) {
            return JsonValue.of(XJSONParser.unquoted(input));
        }
    }
}
