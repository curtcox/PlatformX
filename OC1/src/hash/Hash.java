package hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Curt
 */
public final class Hash {
    
    final Set<Method> methods;
    
    Hash(Method...methods) {
        this.methods = new HashSet<Method>(Arrays.asList(methods));
    }
    
    @Override
    public boolean equals(Object o) {
        Hash that = (Hash) o;
        return methods.equals(that.methods);
    }
    
    @Override
    public int hashCode() {
        return 0;
    }
    
    @Override
    public String toString() {
        return methods.toString();
    } 

    Object invoke(String methodName,Context context,Invokable... args) {
        Method method = getMethod(methodName);
        Context contextWithArgs = context.withArgValues(method.args.args, args);
        return method.invokeIn(contextWithArgs);
    }

    private Method getMethod(String name) {
        for (Method method : methods) {
            if (method.name.equals(name)) {
                return method;
            }
        }
        throw new IllegalArgumentException();
    }

}
