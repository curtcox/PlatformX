package x.net;

import x.io.IO;
import x.util.StringMap;

import java.net.URI;

/**
 * A StringMap that gets values from the network.
 * @author Curt
 */
public final class JsonIndexNetStringMap
    implements StringMap
{

    final URI indexURI;
    final Network network;
    final StringMap.Parser indexParser;

    JsonIndexNetStringMap(URI index, StringMap.Parser indexParser, Network network) {
        this.indexURI = index;
        this.network = network;
        this.indexParser = indexParser;
    }

    public String get(String key) {
        return stringFrom(pageURI(key));
    }
    
    URI pageURI(String key) {
        URI uri = URIs.URI(index().get(key));
        return uri.isAbsolute() ? uri : indexURI.resolve(uri);
    }
    
    StringMap index() {
        return indexParser.parse(stringFrom(indexURI));
    }
    
    private String stringFrom(URI uri) {
        return IO.stringOrEmptyFrom(network.getStreamFor(uri));
    }

}
