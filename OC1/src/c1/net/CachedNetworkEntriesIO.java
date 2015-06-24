package c1.net;

import x.stores.PairIO;
import x.util.Strings;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * PairIO that takes advantage of the fact that the key (URI) is also embedded
 * in the value.
 * @author Curt
 */
final class CachedNetworkEntriesIO
    implements PairIO
{
    final String separator = " ";
    
    public URI readKey(String pair) {
        try {
            return new URI(split(pair)[0]);
        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }
    }
    
    public NetworkCacheEntry readValue(String pair) {
        return NetworkCacheEntry.parse(pair);
    }
    
    private String writeTypedPair(URI key,NetworkCacheEntry value) {
        return value.toString();
    }

    private String[] split(String pair) {
        return Strings.split(pair,separator);
    }

    public String writePair(Object key, Object value) {
        return writeTypedPair((URI)key,(NetworkCacheEntry)value);
    }

}
