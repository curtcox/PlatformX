package hash;

import oc1.util.Objects;

/**
 *
 * @author Curt
 */
public final class Method {

    private static final class Parser
        implements IParser
    {
        public Method parse(Tokens tokens) {
            String name = tokens.next();
            return new Method(name,new Expression.Parser().parse(tokens));
        }    
        
        public boolean canParse(Tokens tokens) {
            return false;
        }

    }

    static Method parse(Tokens tokens) {
        return new Parser().parse(tokens);
    }
    
    final String name;
    final Expression[] expressions;
    
    Method(String name,Expression...expressions) {
        this.name = name;
        this.expressions = expressions;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Method that = (Method) o;
        return name.equals(that.name) &&
               Objects.areEqual(expressions,that.expressions);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
