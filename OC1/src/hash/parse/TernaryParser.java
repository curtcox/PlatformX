package hash.parse;

import hash.Expression;
import hash.Ternary;
import hash.lex.Tokens;

/**
 *
 * @author Curt
 */
final class TernaryParser
    extends AbstractParser
{

    public Ternary parse(Tokens tokens) {
        tokens.verifyNextIs("(");
        ExpressionParser expressions = new ExpressionParser();
        Expression condition = expressions.parse(tokens);
        tokens.verifyNextIs(")");
        tokens.verifyNextIs("?");
        Expression pass = expressions.parse(tokens);
        tokens.verifyNextIs(":");
        Expression fail = expressions.parse(tokens);
        return new Ternary(condition,pass,fail);
    }    

    public boolean canParseTokens(Tokens tokens) {
        if (!tokens.nextIs("("))           { return false; }
        ExpressionParser expressions = new ExpressionParser();
        if (!expressions.canParse(tokens)) { return false; }
        expressions.parse(tokens);
        if (!tokens.nextIs(")"))           { return false; }
        if (!tokens.nextIs("?"))           { return false; }
        if (!expressions.canParse(tokens)) { return false; }
        expressions.parse(tokens);
        if (!tokens.nextIs(":"))           { return false; }
        return expressions.canParse(tokens);
    }
}