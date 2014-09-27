package hash;

/**
 *
 * @author Curt
 */
public abstract class Expression
    implements Invokable
{

    static final Expression EMPTY = new Expression() {
        public Object invokeIn(Context context) {
            return null;
        }
        
        @Override
        public String toString() {
            return "{}";
        }
    };
    
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
