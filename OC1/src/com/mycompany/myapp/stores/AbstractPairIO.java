package com.mycompany.myapp.stores;

/**
 * A skeletal implementation of PairIO.
 * @author Curt
 */
public abstract class AbstractPairIO<K,V>
    implements PairIO<K,V>
{
    public K readKey(String pair) {
        return keyFrom(split(pair)[0]);
    }
    
    public V readValue(String pair) {
        return valueFrom(split(pair)[1]);
    }
    
    public String writePair(K key,V value) {
        return key + "=" + value;
    }

    private String[] split(String pair) {
        int at = pair.indexOf("=");
        String key = pair.substring(0,at);
        String value = pair.substring(at+1, pair.length());
        return new String[] {key,value};
    }

    public abstract K keyFrom(String string);
    public abstract V valueFrom(String string);

}
