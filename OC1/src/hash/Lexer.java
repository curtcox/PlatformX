package hash;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Curt
 */
final class Lexer {

    static String[] split(String string) {
        List<String> parts = new ArrayList<String>();
        boolean quoting = false;
        StringBuilder quoted = new StringBuilder();
        for (String part : parts(string)) {
            if (quoting) {
                quoted.append(part);
                if (quote(part)) {
                    parts.add(quoted.toString());
                    quoting = false;
                    quoted = new StringBuilder();
                }
            } else {
                if (quote(part)) {
                    quoting = true;
                    quoted.append(part);
                } else {
                    if (!part.trim().equals("")) {
                        parts.add(part);
                    }
                }
            }
        }
        return parts.toArray(new String[0]);
    }

    private static boolean quote(String string) {
        return string.equals("\"");
    }
    
    private static String[] parts(String string) {
        return Tokenizer.tokenize(string,Identifier.SPECIAL);
    }

}
