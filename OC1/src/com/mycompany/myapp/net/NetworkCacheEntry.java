package com.mycompany.myapp.net;

import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.mycompany.myapp.Registry;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Curt
 */
final class NetworkCacheEntry {
    
    final String url;
    final String fileName;

    static NetworkCacheEntry newEntryFor(String url) {
        return new NetworkCacheEntry(url,"u_" + Integer.toHexString(url.hashCode()));
    }

    NetworkCacheEntry(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    boolean downloadToStorageWasOK() {
        boolean showProgress = false;
        return Util.downloadUrlToStorage(url, fileName, showProgress);
    }

    InputStream getStreamFromStorage() throws IOException {
        return Registry.get(Storage.class).createInputStream(fileName);
    }

    @Override
    public String toString() {
        return url + " url=>" + fileName;
    }

}
