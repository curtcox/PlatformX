package oc2.net;

import java.net.URI;
import oc1.io.IO;
import oc1.net.Network;
import oc1.net.URIs;
import oc1.util.StringMap;

/**
 *
 * @author Curt
 */
public final class SimpleNetStringMap
    implements StringMap
{
    final URI base;
    final Network network;
    
    SimpleNetStringMap(URI base, Network network) {
        this.base = base;
        this.network = network;
    }

    public String get(String string) {
        return stringFrom(base.resolve(URIs.URI(string)));
    }
    
    private String stringFrom(URI uri) {
        return IO.stringOrEmptyFrom(network.getStreamFor(uri));
    }

}
