package hash.parse;

import hash.Hash;
import hash.Method;
import hash.lex.MethodSourceChunker;
import hash.lex.Tokens;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * For parsing a string into a Hash.
 * @author Curt
 */
public final class HashParser {

    public Hash parse(String source) {
        List<Method> methods = new ArrayList<Method>();
        MethodParser parser = new MethodParser();
        for (Tokens tokens : tokensFrom(source)) {
            methods.add(parser.parse(tokens));
        }
        checkForDups(methods);
        return new Hash(methods.toArray(new Method[0]));
    }
    
    Tokens[] tokensFrom(String source) {
        String[] methodSources = MethodSourceChunker.split(source);
        Tokens[] methodTokens = new Tokens[methodSources.length];
        for (int i=0; i<methodSources.length; i++) {
            methodTokens[i] = Tokens.from(methodSources[i]);
        }
        return methodTokens;
    }

    private void checkForDups(List<Method> methods) {
        Set<String> names = new HashSet<String>();
        for (Method method : methods) {
            if (names.contains(method.name)) {
                throw new IllegalArgumentException("Duplicate method : " + method.name);
            }
            names.add(method.name);
        }
    }
}
