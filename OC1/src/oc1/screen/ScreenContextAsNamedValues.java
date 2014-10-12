package oc1.screen;

import hash.Expression;
import hash.Value;
import hash.NamedValues;

/**
 *
 * @author Curt
 */
final class ScreenContextAsNamedValues
    implements NamedValues
{
    private final ScreenContext context;
    
    public ScreenContextAsNamedValues(ScreenContext context) {
        this.context = context;
    }

    public Expression get(String name) {
        Object value = context.get(name);
        if (value instanceof Expression) {
            return (Expression) value;
        }
        return Value.of(value);
    }
    
}
