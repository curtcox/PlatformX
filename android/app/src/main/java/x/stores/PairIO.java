package x.stores;

/**
 * For converting between strings and typed key-value pairs.
 */
public interface PairIO<K,V> {
    K readKey(String pair);
    V readValue(String pair);
    String writePair(K key,V value);
}
