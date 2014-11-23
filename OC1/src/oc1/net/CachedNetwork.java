package oc1.net;

import com.codename1.io.Storage;
import com.codename1.ui.Image;
import oc1.app.Registry;
import oc1.log.Log;
import oc1.log.LogManager;
import oc1.stores.MapStorageIO;
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

    private final Map<URI,NetworkCacheEntry> entries = new HashMap();
    private final MapStorageIO io;
    private static final String CACHE_ENTRIES = "network_cache_entries";
    
    public CachedNetwork() {
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

    public Image getImage(URI uri) {
        return NetworkCacheEntry.newEntryFor(uri).createImageToStorage();
    }

    public Image getImage(URI uri,int w, int h) {
        return NetworkCacheEntry.newEntryFor(uri).createImageToStorage(w,h);
    }

    private void cacheOnDownloadOK(NetworkCacheEntry entry) {
        if (entry.downloadToStorageWasOK()) {
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

    private Log getLog() {
        return LogManager.of().getLog(CachedNetwork.class);    
    }
}
