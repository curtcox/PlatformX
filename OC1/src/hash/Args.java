package hash;

import java.util.Arrays;
import common.util.Objects;

/**
 *
 * @author curt
 */
public final class Args {
    
    final Expression[] args;
    
    public Args(Expression... args) {
        this.args = args;
    }

    Args valuesFor(Context context) {
        Value[] values = new Value[args.length];
        for (int i=0; i<values.length; i++) {
            values[i] = Value.of(args[i].invokeIn(context));
        }
        return new Args(values);
    }

    static Args valuesFor(Object... objects) {
        Value[] values = new Value[objects.length];
        for (int i=0; i<values.length; i++) {
            values[i] = Value.of(objects[i]);
        }
        return new Args(values);
    }

    @Override
    public int hashCode() {
        return Arrays.asList(args).hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Args that = (Args) o;
        return Objects.areEqual(args, that.args);
    }
    
    @Override
    public String toString() {
        return Arrays.asList(args).toString();
    }
}
