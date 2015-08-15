package hash.lex;

import hash.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * For splitting a source string into tokens.
 * See also Tokenizer, which it uses under-the-covers.
 * @author Curt
 */
final class Lexer {

    private final List<String> parts = new ArrayList<String>();
    private boolean quoting = false;
    private boolean commenting = false;
    private StringBuilder quoted = new StringBuilder();

    private Lexer() {}
    
    private String[] splitIntoParts(String string) {
        for (String part : parts(string)) {
            if (commenting) {
               if (commentEnd(part)) {
                   commenting = false;
               }
            } else if (quoting) {
                quoted.append(part);
                if (quote(part)) {
                    endQuote();
                }
            } else {
                if (commentStart(part)) {
                    commenting = true;
                } else if (quote(part)) {
                    beginQuote(part);
                } else {
                    if (!whitespace(part)) {
                        parts.add(part);
                    }
                }
            }
        }
        return parts.toArray(new String[0]);
    }

    private void beginQuote(String part) {
        quoting = true;
        quoted.append(part);
    }
    
    private void endQuote() {
        parts.add(quoted.toString());
        quoting = false;
        quoted = new StringBuilder();
    }

    private boolean whitespace(String string) {
        return string.trim().equals("");
    }
    
    /**
     * Return the given source string as an array of one-token StringS.
     * The given tokens will not contain any whitespace or comments.
     */
    static String[] split(String string) {
        Lexer lexer = new Lexer();
        return lexer.splitIntoParts(string);
    }

    private static boolean quote(String string) {
        return string.equals("\"");
    }

    private static boolean commentStart(String string) {
        return string.equals("#");
    }

    private static boolean commentEnd(String string) {
        return string.equals("\r") || string.equals("\n");
    }
    
    private static String[] parts(String string) {
        return Tokenizer.tokenize(string,Identifier.SPECIAL);
    }

}
