package hash;

/**
 *
 * @author Curt
 */
public class Expression {

    static final class Parser
        extends CompositeParser
    {
        Parser() {
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
    
}
