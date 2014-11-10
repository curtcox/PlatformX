package oc1.util;

/**
 * An object that can be used to invoke methods on another object.
 * @author Curt
 */
public interface Mirror {

    void setTarget(Object o);
    Object invoke(String method, Object[] args);
    String[] getMethods();
}
