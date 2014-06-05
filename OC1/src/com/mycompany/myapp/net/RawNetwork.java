package com.mycompany.myapp.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Network implementation that does not use cached responses. 
 * @author Curt
 */
public final class RawNetwork
    implements Network
{
    public InputStream getStreamFor(String url) {
        try {
            NetworkCacheEntry entry = NetworkCacheEntry.newEntryFor(url);
            if (!entry.downloadToStorageWasOK()) {
                System.out.println("Download failed");            
            }
            return entry.getStreamFromStorage();
        } catch (IOException e) {
            e.printStackTrace();
            return new ByteArrayInputStream(new byte[0]);
        }
    }
    
}
