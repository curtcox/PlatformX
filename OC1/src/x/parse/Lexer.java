package x.parse;

import java.util.ArrayList;
import java.util.List;

/**
 * Input : tokens with comments and multiple tokens per quoted string
 * Output : tokens with no comments and a single token per quoted string
 * Note: Lexer and Tokenizer could probably be rewritten as a single class that is
 * both simpler and more efficient.
 * @author Curt
 */
public final class Lexer {

    private final List<String> parts = new ArrayList<String>();
    private boolean quoting = false;
    private boolean commenting = false;
    private StringBuilder quoted = new StringBuilder();

    private Lexer() {}
    
    private String[] transformTokens(String[] strings) {
        for (String part : strings) {
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
     * Return the given source strings as an array of one-token StringS.
     * The given tokens will not contain any whitespace or comments.
     */
    public static String[] transform(String[] strings) {
        Lexer lexer = new Lexer();
        return lexer.transformTokens(strings);
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

}
