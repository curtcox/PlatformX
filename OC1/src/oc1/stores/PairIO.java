package oc1.stores;

/**
 * For converting between strings and typed key-value pairs.
 * @author Curt
 */
public interface PairIO<K,V> {
    K readKey(String pair);
    V readValue(String pair);
    String writePair(K key,V value);
}
