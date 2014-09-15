package hash;

/**
 *
 * @author Curt
 */
public abstract class SimpleInvokable
    implements Invokable
{
    SimpleInvokable(String name, String... argNames) {
        
    }

    public Object invokeIn(Context context) {
        Object[] args = null;
        return invoke(args);
    }
    
    public abstract Object invoke(Object[] args);
}
