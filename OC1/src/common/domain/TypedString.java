package common.domain;

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
        if (o==null) {
            return false;
        }
        TypedString that = (TypedString) o;
        return this.getClass().equals(that.getClass()) &&
               this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value==null ? 0 : value.hashCode();
    }
}
