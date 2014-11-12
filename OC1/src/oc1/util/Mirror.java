package oc1.util;

/**
 * An object that can be used to invoke methods on another object.
 * @author Curt
 */
public interface Mirror<T> {

    void setTarget(T o);
    T getTarget();

    Object invoke(String method, Object[] args);
    String[] getMethods();
}
