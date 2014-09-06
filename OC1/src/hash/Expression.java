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
            super(new Constant.Parser());
        }
        
        @Override
        public Expression parse(Tokens tokens) {
            return (Expression) super.parse(tokens);
        } 
    }
    
}
