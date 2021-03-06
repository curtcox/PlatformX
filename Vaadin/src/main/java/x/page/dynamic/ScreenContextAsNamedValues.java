package x.page.dynamic;

import hash.Expression;
import hash.NamedValues;
import hash.Value;

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
