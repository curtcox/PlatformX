package hash;

import java.util.Arrays;
import oc1.util.Objects;

/**
 *
 * @author Curt
 */
public final class Method {

    static final class Parser
        implements IParser
    {
        final Expression.Parser expressions = new Expression.Parser();
        
        public Method parse(Tokens tokens) {
            String name = tokens.next();
            verify(tokens.next(),"{");
            if (tokens.hasNext() && tokens.peek().equals("}")) {
                return new Method(name);
            }
            Expression expression = expressions.parse(tokens);
            verify(tokens.next(),"}");
            return new Method(name,expression);
        }    
        
        public boolean canParse(Tokens tokens) {
            Tokens copy = tokens.copy();
            if (!copy.hasNext() || !Identifier.isValid(copy.next())) {return false;}
            if (!copy.hasNext() || !copy.next().equals("{")) {return false;}
            if (copy.hasNext()  &&  copy.peek().equals("}")) {return true;}
            if (!expressions.canParse(copy))                 {return false;}
            expressions.parse(copy);
            return copy.hasNext() && copy.next().equals("}");
        }

        private void verify(String actual, String expected) {
            if (!expected.equals(actual)) {
                throw new IllegalArgumentException(actual);
            }
        }

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
        return name + Arrays.asList(expressions);
    }
}
