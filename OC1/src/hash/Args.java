package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import oc1.util.Objects;

/**
 *
 * @author curt
 */
final class Args {

    static final class Parser 
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
    
    final Expression[] args;
    
    Args(Expression... args) {
        this.args = args;
    }

    Args valuesFor(Context context) {
        Value[] values = new Value[args.length];
        for (int i=0; i<values.length; i++) {
            values[i] = Value.of(args[i].invokeIn(context));
        }
        return new Args(values);
    }

    static Args valuesFor(Object... objects) {
        Value[] values = new Value[objects.length];
        for (int i=0; i<values.length; i++) {
            values[i] = Value.of(objects[i]);
        }
        return new Args(values);
    }

    @Override
    public int hashCode() {
        return Arrays.asList(args).hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Args that = (Args) o;
        return Objects.areEqual(args, that.args);
    }
    
    @Override
    public String toString() {
        return Arrays.asList(args).toString();
    }
}
