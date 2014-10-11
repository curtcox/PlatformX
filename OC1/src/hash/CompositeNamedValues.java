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
    
    public Object get(String name) {
        for (NamedValues provider : providers) {
            Object value = provider.get(name);
            if (value!=null) {
                return value;
            }
        }
        return null;
    }
    
}
