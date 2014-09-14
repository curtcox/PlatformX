package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import oc1.util.Objects;

/**
 *
 * @author curt
 */
final class ArgNames {

    static final class Parser 
        extends AbstractParser
    {
        public ArgNames parse(Tokens tokens) {
            tokens.verifyNextIs("(");
            List<String> args = new ArrayList<String>();
            while (!tokens.peekIs(")")) {
                args.add(tokens.next());
            }
            tokens.verifyNextIs(")");
            return new ArgNames(args.toArray(new String[0]));
        }    

        public boolean canParseTokens(Tokens tokens) {
            if (!tokens.nextIs("(")) { return false; }
            while (!tokens.peekIs(")")) {
                if (!tokens.hasNext()) { return false;}
                String token = tokens.next();
                if (!Identifier.isValid(token)) {
                    return false;
                }
            }
            return tokens.nextIs(")");
        }
    }
    
    final String[] args;
    
    ArgNames(String... args) {
        this.args = args;
    }
    
    @Override
    public int hashCode() {
        return Arrays.asList(args).hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        ArgNames that = (ArgNames) o;
        return Objects.areEqual(args, that.args);
    }
    
    @Override
    public String toString() {
        return Arrays.asList(args).toString();
    }
}
