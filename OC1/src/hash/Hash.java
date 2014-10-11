package hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A set of named methods.
 * This class is named Hash, rather than NamedMethods, to underscore the
 * central concept of this language as a simple set of named methods.
 * @author Curt
 */
final class Hash
    implements NamedValueProvider
{
    
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

    Object invoke(String methodName,Args args, Context context) {
        Context withArgs = context.withArgValues("#",args);
        return get(methodName).invokeIn(withArgs);
    }

    public Method get(String name) {
        for (Method method : methods) {
            if (method.name.equals(name)) {
                return method;
            }
        }
        throw new IllegalArgumentException(name + " not found in " + methods);
    }

}
