package hash.parse;

import hash.ArgNames;
import hash.Expression;
import hash.Identifier;
import hash.Method;
import hash.lex.Tokens;

/**
 * A parser for MethodS.
 * Contrast with InvocationS.
 * @author Curt
 */
final class MethodParser
    extends AbstractParser
{
    final ArgNamesParser argsParser = new ArgNamesParser();
    final ExpressionParser expressions = new ExpressionParser();

    public Method parse(Tokens tokens) {
        return new Method(tokens.next(),parseArgs(tokens),parseExpression(tokens));
    }    

    private Expression parseExpression(Tokens tokens) {
        tokens.verifyNextIs("{");
        if (tokens.hasNext() && tokens.peek().equals("}")) {
            tokens.verifyNextIs("}");
            return Expression.EMPTY;
        }
        Expression expression = expressions.parse(tokens);
        tokens.verifyNextIs("}");
        return expression;
    }

    private ArgNames parseArgs(Tokens tokens) {
        if (argsParser.canParse(tokens)) {
            return argsParser.parse(tokens);
        } else {
            return new ArgNames();
        }
    }

    public boolean canParseTokens(Tokens tokens) {
        if (!tokens.hasNext() || !Identifier.isValid(tokens.next())) {return false;}
        parseArgs(tokens);
        if (!tokens.nextIs("{"))                                   {return false;}
        if (tokens.peekIs("}"))                                    {return true;}
        if (!expressions.canParse(tokens))                         {return false;}
        expressions.parse(tokens);
        return tokens.nextIs("}");
    }

}