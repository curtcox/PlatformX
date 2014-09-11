package hash;

import java.util.Arrays;
import oc1.util.Objects;

/**
 *
 * @author Curt
 */
public final class Method {

    static final class Parser
        implements IParser
    {
        final Args.Parser argsParser = new Args.Parser();
        final Expression.Parser expressions = new Expression.Parser();
        
        public Method parse(Tokens tokens) {
            String name = tokens.next();
            Args args = new Args();
            if (argsParser.canParse(tokens)) {
                args = argsParser.parse(tokens);
            }
            tokens.verifyNextIs("{");
            if (tokens.hasNext() && tokens.peek().equals("}")) {
                return new Method(name,args);
            }
            Expression expression = expressions.parse(tokens);
            tokens.verifyNextIs("}");
            return new Method(name,args,expression);
        }    
        
        public boolean canParse(Tokens tokens) {
            Tokens copy = tokens.copy();
            if (!copy.hasNext() || !Identifier.isValid(copy.next())) {return false;}
            if (argsParser.canParse(copy)) {
                argsParser.parse(copy);
            }
            if (!copy.nextIs("{"))                                   {return false;}
            if (copy.peekIs("}"))                                    {return true;}
            if (!expressions.canParse(copy))                         {return false;}
            expressions.parse(copy);
            return copy.nextIs("}");
        }

    }

    final String name;
    final Args args;
    final Expression[] body;
    
    Method(String name,Expression...body) {
        this(name,new Args(),body);
    }

    Method(String name,Args args, Expression...body) {
        this.name = name;
        this.args = args;
        this.body = body;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Method that = (Method) o;
        return name.equals(that.name) &&
               args.equals(that.args) &&
               Objects.areEqual(body,that.body);
    }
    
    @Override
    public String toString() {
        return name + args.toString() + Arrays.asList(body);
    }
}
