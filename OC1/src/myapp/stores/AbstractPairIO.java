package myapp.stores;

import myapp.util.Strings;

/**
 * A skeletal implementation of PairIO.
 * @author Curt
 */
public abstract class AbstractPairIO<K,V>
    implements PairIO<K,V>
{
    final String separator;
    
    public AbstractPairIO(String separator) {
        this.separator = separator;    
    }
    
    public K readKey(String pair) {
        return keyFrom(split(pair)[0]);
    }
    
    public V readValue(String pair) {
        return valueFrom(split(pair)[1]);
    }
    
    public String writePair(K key,V value) {
        return key + separator + value;
    }

    private String[] split(String pair) {
        return Strings.split(pair,separator);
    }

    public abstract K keyFrom(String string);
    public abstract V valueFrom(String string);

}
