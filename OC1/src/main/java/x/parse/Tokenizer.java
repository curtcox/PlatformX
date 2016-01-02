package x.parse;

import java.util.*;

/**
 * For splitting a string into tokens.
 * See also Lexer, which can be used to wrap the output of a
 * Tokenizer to correctly handle quotes and comments.
 * Note: Lexer and Tokenizer could probably be rewritten as a single class that is
 * both simpler and more efficient.
 * @author Curt
 */
public final class Tokenizer {

    final List<String> tokens = new ArrayList<String>();
    final Set<String> separators;
    StringBuilder token = null;

    Tokenizer(String... separators) {
        this.separators = new HashSet<String>(Arrays.asList(separators));
    }

    String[] tokenize(String string) {
        for (int i=0; i<string.length(); i++) {
            String c = string.substring(i,i+1);
            if (isSeparator(c)) {
                addCurrentTokenToTokens();
                tokens.add(c);
            } else {
                addCharacterToCurrentToken(c);
            }
        }
        addCurrentTokenToTokens();
        return tokens.toArray(new String[0]);
    }

    private void addCurrentTokenToTokens() {
        if (token!=null) {
            tokens.add(token.toString());
            token = null;
        }
    }

    void addCharacterToCurrentToken(String c) {
        if (token==null) {
            token = new StringBuilder();
        }
        token.append(c);
    }

    boolean isSeparator(String c) {
        return separators.contains(c);
    }

    /**
     * Use the separators to transform the string into tokens.
     * The separators are included in the returned tokens.
     * @param string the string to be spilt
     * @param separators the separator tokens to transform on
     * @return the separator tokens and the strings between them.
     */
    public static String[] tokenize(String string,String... separators) {
        checkSeparators(separators);
        Tokenizer tokenizer = new Tokenizer(separators);
        return tokenizer.tokenize(string);
    }

    private static void checkSeparators(String[] separators) {
        for (String separator : separators) {
            if (separator.length()!=1) {
                String message = "All separators must be single characters";
                throw new IllegalArgumentException(message);
            }
        }
    }

}
