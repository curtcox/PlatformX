package hash.parse;

import hash.ArgNames;
import hash.Identifier;
import hash.lex.Tokens;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author curt
 */
final class ArgNamesParser
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
