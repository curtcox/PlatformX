package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Curt
 */
public final class Tokens {

    final List<String>tokens;
    
    static Tokens from(String string) {
        return new Tokens(Lexer.split(string));
    }

    private Tokens(String[] tokens) {
        this.tokens = new ArrayList(Arrays.asList(tokens));
    }

    boolean hasNext() {
        return !tokens.isEmpty();
    }

    String next() {
        return tokens.remove(0);
    }

    String peek() {
        return tokens.get(0);
    }

    Tokens copy() {
        return new Tokens(tokens.toArray(new String[0]));
    }
    
}
