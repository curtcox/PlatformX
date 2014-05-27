package com.mycompany.myapp.net;

import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.mycompany.myapp.Registry;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Curt
 */
final class NetworkCache {

    final Map<String,NetworkCacheEntry> entries = new HashMap();

    public InputStream getStreamFor(String url) {
        try {
            NetworkCacheEntry entry = newEntryFor(url);
            if (entries.containsKey(url)) {
                return getStreamFromStorage(entry);
            }
            if (downloadToStorageWasOK(entry)) {
                entries.put(url, entry);
            } else {
                System.out.println("Download failed");            
            }
            return getStreamFromStorage(entry);
        } catch (IOException e) {
            e.printStackTrace();
            return new ByteArrayInputStream(new byte[0]);
        }
    }

    private NetworkCacheEntry newEntryFor(String url) {
        return new NetworkCacheEntry(url,"u_" + Integer.toHexString(url.hashCode()));
    }

    boolean downloadToStorageWasOK(NetworkCacheEntry entry) {
        boolean showProgress = true;
        return Util.downloadUrlToStorage(entry.url, entry.fileName, showProgress);
    }
    
    InputStream getStreamFromStorage(NetworkCacheEntry entry) throws IOException {
        return Registry.get(Storage.class).createInputStream(entry.fileName);
    }
}
