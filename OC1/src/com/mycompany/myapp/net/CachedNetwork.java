package com.mycompany.myapp.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Network implementation that uses cached responses. 
 * @author Curt
 */
public final class CachedNetwork
    implements Network
{

    final Map<String,NetworkCacheEntry> entries = new HashMap();

    public InputStream getStreamFor(String url) {
        try {
            NetworkCacheEntry entry = NetworkCacheEntry.newEntryFor(url);
            if (entries.containsKey(url)) {
                return entry.getStreamFromStorage();
            }
            if (entry.downloadToStorageWasOK()) {
                entries.put(url, entry);
            } else {
                System.out.println("Download failed");            
            }
            return entry.getStreamFromStorage();
        } catch (IOException e) {
            e.printStackTrace();
            return new ByteArrayInputStream(new byte[0]);
        }
    }

}