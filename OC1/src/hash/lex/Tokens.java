package hash.lex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Curt
 */
public final class Tokens {

    final List<String>tokens;
    
    public static Tokens from(String string) {
        return new Tokens(Lexer.split(string));
    }

    private Tokens(String[] tokens) {
        this.tokens = new ArrayList(Arrays.asList(tokens));
    }

    public boolean hasNext() {
        return !tokens.isEmpty();
    }

    public String next() {
        return tokens.remove(0);
    }

    public String peek() {
        return tokens.get(0);
    }

    public Tokens copy() {
        return new Tokens(tokens.toArray(new String[0]));
    }

    public boolean nextIs(String value) {
        return hasNext() && next().equals(value);
    }

    public boolean peekIs(String value) {
        return hasNext() && peek().equals(value);
    }

    public void verifyNextIs(String expected) {
        if (!expected.equals(next())) {
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public String toString() {
        return tokens.toString();
    }
}
