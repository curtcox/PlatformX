package hash.parse;

import hash.Args;
import hash.Expression;
import hash.Identifier;
import hash.lex.Tokens;
import java.util.ArrayList;
import java.util.List;

final class ArgsParser
    extends AbstractParser
{
    public Args parse(Tokens tokens) {
        tokens.verifyNextIs("(");
        List<Expression> args = new ArrayList<Expression>();
        ExpressionParser expressions = new ExpressionParser();
        while (!tokens.peekIs(")")) {
            args.add(expressions.parse(tokens));
        }
        tokens.verifyNextIs(")");
        return new Args(args.toArray(new Expression[0]));
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
    
