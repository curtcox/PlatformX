package hash;

/**
 *
 * @author Curt
 */
final class CompositeNamedValues
    implements NamedValues
{
    final NamedValues[] providers;
    
    CompositeNamedValues(NamedValues... providers) {
        this.providers = providers;
    }
    
    public Expression get(String name) {
        for (NamedValues provider : providers) {
            Expression value = provider.get(name);
            if (value!=null) {
                return value;
            }
        }
        return null;
    }
    
}
