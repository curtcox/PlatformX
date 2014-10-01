package oc2.net;

import java.net.URI;
import java.net.URISyntaxException;
import oc1.net.Network;
import oc1.io.IO;
import oc1.io.JSON;
import oc1.util.StringMap;

/**
 *
 * @author Curt
 */
public final class NetStringMap
    implements StringMap
{

    final URI indexURI;
    final Network network;
    
    NetStringMap(URI index, Network network) {
        this.indexURI = index;
        this.network = network;
    }

    public String get(String key) {
        return stringFrom(URI(index().get(key)));
    }
    
    StringMap index() {
        return JSON.stringMapFrom(stringFrom(indexURI));
    }
    
    private String stringFrom(URI uri) {
        return IO.stringOrEmptyFrom(network.getStreamFor(uri));
    }

    private URI URI(String string) {
        try {
            return new URI(string);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(string);
        }
    }
}
