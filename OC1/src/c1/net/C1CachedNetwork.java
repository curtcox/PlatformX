package c1.net;

import com.codename1.io.Storage;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.net.Network;
import x.stores.MapStorageIO;
import x.uiwidget.UIImage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Network implementation that uses cached responses. 
 */
public final class C1CachedNetwork
    implements Network
{

    private final Map<URI,NetworkCacheEntry> entries = new HashMap();
    private final MapStorageIO io;
    private static final String CACHE_ENTRIES = "network_cache_entries";
    
    public C1CachedNetwork() {
        io = new MapStorageIO(getStorage(),new CachedNetworkEntriesIO(),CACHE_ENTRIES);
        loadEntriesFromStorage();    
    }

    private Storage getStorage() {
        return Registry.get(Storage.class);
    }

    public InputStream getStreamFor(URI uri) {
        log("getStreamFor " + uri);
        try {
            if (entries.containsKey(uri)) {
                return entries.get(uri).getStreamFromStorage();
            }
            NetworkCacheEntry entry = NetworkCacheEntry.newEntryFor(uri);
            cacheOnDownloadOK(entry);
            return entry.getStreamFromStorage();
        } catch (IOException e) {
            log(e);
            return emptyStream();
        }
    }

    public UIImage getImage(URI uri) {
        return NetworkCacheEntry.newEntryFor(uri).createImageToStorage();
    }

    public UIImage getImage(URI uri,int w, int h) {
        return NetworkCacheEntry.newEntryFor(uri).createImageToStorage(w,h);
    }

    private void cacheOnDownloadOK(NetworkCacheEntry entry) {
        if (entry.blockingDownloadToStorageWhichReturnsTrueOnSuccess()) {
            entries.put(entry.uri, entry);
            saveEntriesToStorage();
        } else {
            log("Download failed");
        }
    }
    
    private InputStream emptyStream() {
        return new ByteArrayInputStream(new byte[0]);
    }

    private void loadEntriesFromStorage() {
        entries.putAll(io.readMap());
    }

    private void saveEntriesToStorage() {
        io.writeMap(entries);
    }

    private void log(Exception e) {
        getLog().log(e);    
    }

    private void log(String message) {
        getLog().log(message);    
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(C1CachedNetwork.class);
    }
}
