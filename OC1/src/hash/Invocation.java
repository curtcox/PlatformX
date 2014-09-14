package hash;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Curt
 */
public final class Invocation 
    extends Expression
{

    static final class Parser
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
                Expression.Parser expressions = new Expression.Parser();
                while (!tokens.peekIs(")")) {
                    args.add(expressions.parse(tokens));
                }
                tokens.next();
            }
            return args;
        }
        
        public boolean canParseTokens(Tokens tokens) {
            if (!tokens.hasNext() || !Identifier.isValid(tokens.next())) { return false; }
            if (!tokens.nextIs("("))                                   { return true; }
            Expression.Parser expressions = new Expression.Parser();
            Return.Parser returns = new Return.Parser();
            while (!tokens.peekIs(")")) {
                if (returns.canParse(tokens))                          { return false;}
                if (!expressions.canParse(tokens))                     { return false; }
                else {
                    expressions.parse(tokens);
                }
            }
            return tokens.nextIs(")");
        }

    }
    
    final String value;
    final Args args;
    
    Invocation(String value) {
        this(value,new Args());
    }

    Invocation(String value,Args args) {
        this.value = value;
        this.args = args;
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Invocation that = (Invocation) o;
        return value.equals(that.value) &&
               args.equals(that.args);
    }
    
    @Override
    public String toString() {
        return value + args;
    }
}
