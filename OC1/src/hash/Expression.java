package hash;

/**
 * 
 * @author Curt
 */
final class Expression {

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
        public Invokable parse(Tokens tokens) {
            return (Invokable) super.parse(tokens);
        } 
    }
    
}
