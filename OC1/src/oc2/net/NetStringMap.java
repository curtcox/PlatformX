package oc2.net;

import java.net.URI;
import oc1.net.Network;
import oc1.util.StringMap;

/**
 *
 * @author Curt
 */
public final class NetStringMap
    implements StringMap
{

    URI uri;
    Network network;
    
    NetStringMap(URI uri, Network network) {
        this.uri = uri;
        this.network = network;
    }

    public String get(String string) {
        return "";
    }
    
}
