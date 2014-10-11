package oc1.screen;

import hash.Invokable;
import hash.Value;
import hash.NamedValueProvider;

/**
 *
 * @author Curt
 */
final class ScreenContextAsNamedInvokableProvider
    implements NamedValueProvider<Invokable>
{
    private final ScreenContext context;
    
    public ScreenContextAsNamedInvokableProvider(ScreenContext context) {
        this.context = context;
    }

    public Invokable get(String name) {
        Object value = context.get(name);
        if (value instanceof Invokable) {
            return (Invokable) value;
        }
        return new Value(value);
    }
    
}
