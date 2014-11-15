package hash.parse;

import hash.Hash;
import hash.Method;
import hash.lex.Tokens;
import java.util.ArrayList;
import java.util.List;

/**
 * For parsing a string into a Hash.
 * @author Curt
 */
public final class HashParser {

    public Hash parse(String source) {
        List<Method> methods = new ArrayList<Method>();
        ValidMethodParser parser = new ValidMethodParser();
        Tokens tokens = Tokens.from(source);
        while (parser.canParse(tokens)) {
            methods.add(parser.parse(tokens));
        }
        return new Hash(methods.toArray(new Method[0]));
    }
    
}
