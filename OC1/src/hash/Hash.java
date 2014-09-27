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

    public static Object invoke(String source, String methodName,Context context, Object... args) {
        return parse(source).invoke(methodName, Args.valuesFor(args), context);
    }
    
    static Hash parse(String source) {
        return new Parser().parse(source);
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
        return getMethod(methodName).invokeIn(withArgs);
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
