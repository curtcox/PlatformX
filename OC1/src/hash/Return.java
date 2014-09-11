package hash;

/**
 *
 * @author Curt
 */
public final class Return
    extends Expression
{
    static final class Parser
        implements IParser
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

        public boolean canParse(Tokens tokens) {
            Tokens copy = tokens.copy();
            return copy.nextIs("^");
        }
    }
    
    final Expression expression;
    
    Return(Expression expression) {
        this.expression = expression;
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
