package hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
            if (!validIdentifier(value)) {
                throw new IllegalArgumentException();
            }
            return new Invocation(value);
        }    

        public boolean canParse(Tokens tokens) {
            Tokens copy = tokens.copy();
            return copy.hasNext() && validIdentifier(copy.next());
        }

        private boolean validIdentifier(String value) {
            Set<String> reject = new HashSet(Arrays.asList("\"","?",":",".","^"));
            for (int i=0; i<value.length(); i++) {
                String c = value.substring(i, i+1);
                if (reject.contains(c)) {
                    return false;
                }
            }
            return true;
        }
    }
    
    final String value;
    
    Invocation(String value) {
        this.value = value;
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Invocation that = (Invocation) o;
        return value.equals(that.value);
    }
}
