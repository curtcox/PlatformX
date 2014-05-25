package com.mycompany.myapp.net;

import java.io.InputStream;

/**
 * An abstraction for network requests that implements caching.
 * @author Curt
 */
public final class Network {
    
    final NetworkCache cache = new NetworkCache();
    
    public InputStream getStreamFor(String url) {
        return cache.getStreamFor(url);
    }

}
