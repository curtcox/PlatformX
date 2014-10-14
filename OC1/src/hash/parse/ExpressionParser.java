package hash.parse;

import hash.Expression;
import hash.lex.Tokens;

/**
 * For parsing expressions.
 * @author Curt
 */
final class ExpressionParser
    extends CompositeParser
{
    ExpressionParser() {
        super(
            new NumericConstantParser(),
            new StringConstantParser(),
            new InvocationParser(),
            new TernaryParser(),
            new ReturnParser());
    }

    @Override
    public Expression parse(Tokens tokens) {
        return (Expression) super.parse(tokens);
    } 
}
