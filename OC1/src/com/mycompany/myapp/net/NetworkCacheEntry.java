package com.mycompany.myapp.net;

/**
 *
 * @author Curt
 */
final class NetworkCacheEntry {
    
    final String url;
    final String fileName;

    NetworkCacheEntry(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return url + " url=>" + fileName;
    }

}
