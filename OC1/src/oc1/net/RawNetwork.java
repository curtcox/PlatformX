package oc1.net;

import com.codename1.ui.Image;
import oc1.log.Log;
import oc1.log.LogManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Network implementation that does not use cached responses. 
 * @author Curt
 */
public final class RawNetwork
    implements Network
{
    public InputStream getStreamFor(URI url) {
        try {
            NetworkCacheEntry entry = NetworkCacheEntry.newEntryFor(url);
            if (!entry.downloadToStorageWasOK()) {
                log("Download failed for " + url);            
            }
            return entry.getStreamFromStorage();
        } catch (IOException e) {
            log(e);
            return new ByteArrayInputStream(new byte[0]);
        }
    }

    public Image getImage(URI uri) {
        return NetworkCacheEntry.newEntryFor(uri).createImageToStorage();
    }

    private void log(Exception e) {
        getLog().log(e);    
    }

    private void log(String message) {
        getLog().log(message);    
    }

    private Log getLog() {
        return LogManager.of().getLog(RawNetwork.class);    
    }

}
