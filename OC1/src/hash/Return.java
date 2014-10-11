package hash;

/**
 *
 * @author Curt
 */
final class Return
    implements Invokable
{
    static final class Parser
        extends AbstractParser
    {
        public Return parse(Tokens tokens) {
            verifyReturn(tokens.next());
            return new Return(new Expression.Parser().parse(tokens));
        }    

        private void verifyReturn(String string) {
            if (!string.equals("^")) {
                throw new IllegalArgumentException();
            }
        }

        public boolean canParseTokens(Tokens tokens) {
            return tokens.nextIs("^");
        }
    }
    
    final Invokable expression;
    
    Return(Invokable expression) {
        this.expression = expression;
    }
    
    public Object invokeIn(Context context) {
        return expression.invokeIn(context);
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Return that = (Return) o;
        return expression.equals(that.expression);
    }
    
    @Override
    public String toString() {
        return "^" + expression;
    }
}
