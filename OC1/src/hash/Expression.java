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
            super(new Constant.Parser(),new Invocation.Parser(),new Return.Parser());
        }
        
        @Override
        public Expression parse(Tokens tokens) {
            return (Expression) super.parse(tokens);
        } 
    }
    
}
