package oc2.net;

import oc1.net.URIs;
import oc1.util.StringMap;

public final class RootStringMap {
    
    public static StringMap of() {
        return new SimpleNetStringMap(URIs.URI("http://localhost:8000/"));
    }
}
