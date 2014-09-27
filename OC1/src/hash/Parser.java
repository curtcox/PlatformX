package hash;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Curt
 */
final class Parser {

    Hash parse(String source) {
        List<Method> methods = new ArrayList<Method>();
        Method.Parser parser = new Method.Parser();
        Tokens tokens = Tokens.from(source);
        while (parser.canParse(tokens)) {
            methods.add(parser.parse(tokens));
        }
        return new Hash(methods.toArray(new Method[0]));
    }
    
}
