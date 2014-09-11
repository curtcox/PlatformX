package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import oc1.util.Objects;

/**
 *
 * @author Curt
 */
public final class Invocation 
    extends Expression
{

    static final class Parser
        implements IParser
    {
        public Invocation parse(Tokens tokens) {
            String value = tokens.next();
            if (!Identifier.isValid(value)) {
                throw new IllegalArgumentException();
            }
            List<String> args = new ArrayList<String>();
            if (tokens.peekIs("(")) {
                tokens.next();
                while (!tokens.peekIs(")")) {
                    args.add(tokens.next());
                }
                tokens.next();
            }
            return new Invocation(value,args.toArray(new String[0]));
        }    

        public boolean canParse(Tokens tokens) {
            Tokens copy = tokens.copy();
            if (!copy.hasNext() || !Identifier.isValid(copy.next())) { return false; }
            if (!copy.nextIs("("))                                   { return true; }
            while (!copy.peekIs(")")) {
                String token = copy.next();
                if (!Identifier.isValid(token))                      { return false; }
            }
            return copy.nextIs(")");
        }

    }
    
    final String value;
    final String[] params;
    
    Invocation(String value,String...params) {
        this.value = value;
        this.params = params;
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Invocation that = (Invocation) o;
        return value.equals(that.value) &&
               Objects.areEqual(params, that.params);
    }
    
    @Override
    public String toString() {
        return value + Arrays.asList(params);
    }
}
