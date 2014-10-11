package hash;

/**
 * 
 * @author Curt
 */
final class ExpressionParser
    extends CompositeParser
{
    ExpressionParser() {
        super(
            new NumericConstant.Parser(),
            new StringConstant.Parser(),
            new Invocation.Parser(),
            new Ternary.Parser(),
            new Return.Parser());
    }

    @Override
    public Expression parse(Tokens tokens) {
        return (Expression) super.parse(tokens);
    } 
}
