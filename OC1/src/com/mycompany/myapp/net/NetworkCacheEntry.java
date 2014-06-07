package com.mycompany.myapp.net;

import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.mycompany.myapp.Registry;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 *
 * @author Curt
 */
final class NetworkCacheEntry {
    
    final URI url;
    final String fileName;

    static NetworkCacheEntry newEntryFor(URI url) {
        return new NetworkCacheEntry(url,"u_" + Integer.toHexString(url.hashCode()));
    }

    NetworkCacheEntry(URI url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    boolean downloadToStorageWasOK() {
        boolean showProgress = false;
        return Util.downloadUrlToStorage(url.toString(), fileName, showProgress);
    }

    InputStream getStreamFromStorage() throws IOException {
        return Registry.get(Storage.class).createInputStream(fileName);
    }

    @Override
    public String toString() {
        return url + " url=>" + fileName;
    }

    Image createImageToStorage() {
        EncodedImage placeholder = new EncodedImage(71, 71){};
        URLImage image = URLImage.createToStorage(placeholder, fileName, url.toString(), URLImage.RESIZE_SCALE);
        image.fetch();
        return image;
    }

}
