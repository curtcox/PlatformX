package hash.parse;

import hash.Args;
import hash.Expression;
import hash.Identifier;
import hash.Invocation;
import hash.lex.Tokens;

import java.util.ArrayList;
import java.util.List;

/**
 * A named runtime invocation, possibly with arguments.
 * Invocations can be used as part of a method definition.
 * Depending on the number of arguments and the context used for invocation,
 * this could be thought of as method invocation or a variable lookup.
 * @author Curt
 */
final class InvocationParser 
        extends AbstractParser
{
    public Invocation parse(Tokens tokens) {
        String value = tokens.next();
        if (!Identifier.isValid(value)) {
            throw new IllegalArgumentException();
        }
        List<Expression> args = parseArgs(tokens);
        return new Invocation(value,new Args(args.toArray(new Expression[0])));
    }    

    List<Expression> parseArgs(Tokens tokens) {
        List<Expression> args = new ArrayList<Expression>();
        if (tokens.peekIs("(")) {
            tokens.next();
            ExpressionParser expressions = new ExpressionParser();
            while (!tokens.peekIs(")")) {
                args.add(expressions.parse(tokens));
            }
            tokens.next();
        }
        return args;
    }

    public boolean canParseTokens(Tokens tokens) {
        if (!tokens.hasNext() || !Identifier.isValid(tokens.next())) { return false; }
        if (!tokens.nextIs("("))                                     { return true; }
        ExpressionParser expressions = new ExpressionParser();
        while (!tokens.peekIs(")")) {
            if (!expressions.canParse(tokens))                       { return false; }
            else {
                expressions.parse(tokens);
            }
        }
        return tokens.nextIs(")");
    }

}
