package x.reflect;

import x.reflect.Mirror;

import java.util.Arrays;

public abstract class AbstractMirror<T>
    implements Mirror<T>
{
    public T target;
    public final Class targetClass;
    public final String[] methods;
    
    public AbstractMirror(Class targetClass, String... methods) {
        this.targetClass = targetClass;
        this.methods = methods;
    }
    
    public void setTarget(T target) {
        this.target = target;
    }

    public T getTarget() {
        return target;
    }

    public final String[] getMethods() {
        return methods;
    }
 
    protected final IllegalArgumentException methodNotFound(String method, Object[] args) {
        throw new IllegalArgumentException(method + Arrays.asList(args));
    }

}
