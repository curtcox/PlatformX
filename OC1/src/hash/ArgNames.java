package hash;

import java.util.Arrays;
import oc1.util.Objects;

public final class ArgNames {
    
    final String[] args;
    
    public ArgNames(String... args) {
        this.args = args;
    }
    
    @Override
    public int hashCode() {
        return Arrays.asList(args).hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        ArgNames that = (ArgNames) o;
        return Objects.areEqual(args, that.args);
    }
    
    @Override
    public String toString() {
        return Arrays.asList(args).toString();
    }
}
