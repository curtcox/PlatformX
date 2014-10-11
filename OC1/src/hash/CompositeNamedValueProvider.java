package hash;

/**
 *
 * @author Curt
 */
final class CompositeNamedValueProvider
    implements NamedValueProvider
{
    final NamedValueProvider[] providers;
    
    CompositeNamedValueProvider(NamedValueProvider... providers) {
        this.providers = providers;
    }
    
    public Object get(String name) {
        for (NamedValueProvider provider : providers) {
            Object value = provider.get(name);
            if (value!=null) {
                return value;
            }
        }
        return null;
    }
    
}
