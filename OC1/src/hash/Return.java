package hash;

/**
 *
 * @author Curt
 */
public final class Return
    extends Expression
{
    static final class Parser {
        Return parse(Tokens tokens) {
            verifyReturn(tokens.next());
            return new Return(new Expression.Parser().parse(tokens));
        }    

        private void verifyReturn(String string) {
            if (!string.equals("return")) {
                throw new IllegalArgumentException();
            }
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
    
}
