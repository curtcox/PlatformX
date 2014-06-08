package oc1.domain;

/**
 * A type safe string.
 * @author Curt
 */
public class TypedString {
    final String value;
    
    public TypedString(String name) {
        this.value = name;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        TypedString that = (TypedString) o;
        return this.getClass().equals(that.getClass()) &&
               this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
