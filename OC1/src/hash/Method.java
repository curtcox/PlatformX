package hash;

import oc1.util.Objects;

/**
 *
 * @author Curt
 */
public final class Method {

    static final class Parser {
        Method parse(Tokens tokens) {
            String value = tokens.next();
            return new Method(value);
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
        return name;
    }
}
