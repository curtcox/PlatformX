package hash;

/**
 * A method definition.
 * Contrast with Invocation.
 * @author Curt
 */
final class Method
    implements Expression
{

    static final class Parser
        extends AbstractParser
    {
        final ArgNames.Parser argsParser = new ArgNames.Parser();
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

    final String name;
    final ArgNames args;
    final Expression body;
    
    Method(String name,Expression body) {
        this(name,new ArgNames(),body);
    }

    Method(String name,ArgNames args, Expression body) {
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
               body.equals(that.body);
    }

    public Object invokeIn(Context context) {
        return body.invokeIn(context.withArgNames(name,args));
    }

    @Override
    public String toString() {
        return name + args.toString() + body;
    }
}
