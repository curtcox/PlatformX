package oc1.screen;

import hash.Expression;
import hash.Value;
import hash.NamedValueProvider;

/**
 *
 * @author Curt
 */
final class ScreenContextAsNamedInvokableProvider
    implements NamedValueProvider<Expression>
{
    private final ScreenContext context;
    
    public ScreenContextAsNamedInvokableProvider(ScreenContext context) {
        this.context = context;
    }

    public Expression get(String name) {
        Object value = context.get(name);
        if (value instanceof Expression) {
            return (Expression) value;
        }
        return new Value(value);
    }
    
}
