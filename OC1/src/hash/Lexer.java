package hash;

import java.util.ArrayList;
import java.util.List;
import oc1.util.Tokenizer;

/**
 *
 * @author Curt
 */
public final class Lexer {

    static String[] split(String string) {
        List<String> parts = new ArrayList<String>();
        for (String part : parts(string)) {
            if (!part.trim().equals("")) {
                parts.add(part);
            }
        }
        return parts.toArray(new String[0]);
    }

    private static String[] parts(String string) {
        return Tokenizer.tokenize(string,
            " ", ".", "?", ":", ",", "\"",
            "{", "}",
            "(", ")"
        );
    }

}
