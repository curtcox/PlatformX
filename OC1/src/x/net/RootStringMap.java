package x.net;

import x.util.StringMap;

public final class RootStringMap {
    
    public static StringMap of() {
        return new SimpleNetStringMap(URIs.URI("http://localhost:8000/"));
    }
}
