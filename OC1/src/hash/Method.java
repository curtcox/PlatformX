package hash;

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
    
    Method(String name,Expression...expressions) {
        this.name = name;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Method that = (Method) o;
        return name.equals(that.name);
    }
    
}
