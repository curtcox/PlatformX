package c1.net;

import x.log.ILog;
import x.log.ILogManager;
import x.Registry;
import x.net.Network;
import x.uiwidget.UIImage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Network implementation that does not use cached responses. 
 */
public final class C1RawNetwork
    implements Network
{
    public InputStream getStreamFor(URI url) {
        try {
            NetworkCacheEntry entry = NetworkCacheEntry.newEntryFor(url);
            if (!entry.blockingDownloadToStorageWhichReturnsTrueOnSuccess()) {
                log("Download failed for " + url);            
            }
            return entry.getStreamFromStorage();
        } catch (IOException e) {
            log(e);
            return new ByteArrayInputStream(new byte[0]);
        }
    }

    public UIImage getImage(URI uri) {
        return NetworkCacheEntry.newEntryFor(uri).createImageToStorage();
    }

    public UIImage getImage(URI uri, int w, int h) {
        return NetworkCacheEntry.newEntryFor(uri).createImageToStorage();
    }

    private void log(Exception e) {
        getLog().log(e);    
    }

    private void log(String message) {
        getLog().log(message);    
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(C1RawNetwork.class);
    }

}
