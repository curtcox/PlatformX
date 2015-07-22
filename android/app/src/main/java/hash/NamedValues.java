package hash;

/**
 *
 * @author Curt
 * @param <V>
 */
public interface NamedValues {
    /**
    * Returns the value.
    * @param name
    * @return the value
    */
   Expression get(String name);

}
