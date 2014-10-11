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
    public Invokable parse(Tokens tokens) {
        return (Invokable) super.parse(tokens);
    } 
}
