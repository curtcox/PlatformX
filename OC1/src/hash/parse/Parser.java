package hash.parse;

import hash.Hash;
import hash.Method;
import hash.lex.Tokens;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Curt
 */
public final class Parser {

    public Hash parse(String source) {
        List<Method> methods = new ArrayList<Method>();
        MethodParser parser = new MethodParser();
        Tokens tokens = Tokens.from(source);
        while (parser.canParse(tokens)) {
            methods.add(parser.parse(tokens));
        }
        return new Hash(methods.toArray(new Method[0]));
    }
    
}
