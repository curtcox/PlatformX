package com.mycompany.myapp.net;

import com.codename1.ui.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Network implementation that uses cached responses. 
 * @author Curt
 */
public final class CachedNetwork
    implements Network
{

    final Map<URI,NetworkCacheEntry> entries = new HashMap();

    public InputStream getStreamFor(URI uri) {
        try {
            if (entries.containsKey(uri)) {
                return entries.get(uri).getStreamFromStorage();
            }
            NetworkCacheEntry entry = NetworkCacheEntry.newEntryFor(uri);
            cacheOnDownloadOK(entry);
            return entry.getStreamFromStorage();
        } catch (IOException e) {
            e.printStackTrace();
            return emptyStream();
        }
    }

    public Image getImage(URI uri) {
        return NetworkCacheEntry.newEntryFor(uri).createImageToStorage();
    }

    private void cacheOnDownloadOK(NetworkCacheEntry entry) {
        if (entry.downloadToStorageWasOK()) {
            entries.put(entry.uri, entry);
        } else {
            System.out.println("Download failed");            
        }
    }
    
    private InputStream emptyStream() {
        return new ByteArrayInputStream(new byte[0]);
   }

}
