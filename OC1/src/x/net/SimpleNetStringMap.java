package x.net;

import java.net.URI;
import x.IO;
import x.Registry;
import x.util.StringMap;

public final class SimpleNetStringMap
    implements StringMap
{
    final URI base;
    final Network network;
    
    public SimpleNetStringMap(URI base)
    {
        this(base,network());
    }

    SimpleNetStringMap(URI base, Network network) {
        this.base = base;
        this.network = network;
    }

    private static Network network() {
        return Registry.get(Network.class);
    }

    public String get(String string) {
        return stringFrom(base.resolve(URIs.URI(string)));
    }
    
    private String stringFrom(URI uri) {
        return IO.stringOrEmptyFrom(network.getStreamFor(uri));
    }

}
