package hash;

import java.util.Arrays;
import oc1.util.Objects;

/**
 *
 * @author curt
 */
final class Args {

        static final class Parser 
        implements IParser
    {
        public Args parse(Tokens tokens) {
            return new Args();
        }    

        public boolean canParse(Tokens tokens) {
            return false;
        }
    }
    
    final String[] params;
    
    Args(String... params) {
        this.params = params;
    }
    
    @Override
    public int hashCode() {
        return Arrays.asList(params).hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Args that = (Args) o;
        return Objects.areEqual(params, that.params);
    }
    
    @Override
    public String toString() {
        return Arrays.asList(params).toString();
    }
}
