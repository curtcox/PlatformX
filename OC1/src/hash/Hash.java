package hash;

/**
 *
 * @author Curt
 */
public final class Hash {
    
    final int size;
    
    Hash(Method...methods) {
        size = methods.length;
    }
    
    @Override
    public boolean equals(Object o) {
        Hash that = (Hash) o;
        return size == that.size;
    }
    
    @Override
    public int hashCode() {
        return 0;
    }
}
