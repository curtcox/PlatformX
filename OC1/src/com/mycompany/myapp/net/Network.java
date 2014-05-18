package com.mycompany.myapp.net;

import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.mycompany.myapp.Registry;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * An abstraction for network requests that implements caching.
 * @author Curt
 */
public final class Network {
    
    public InputStream getStreamFor(String url) {
        String fileName = "temp";
        boolean showProgress = true;
        if (!Util.downloadUrlToStorage(url, fileName, showProgress)) {
            System.out.println("Download failed");            
        }
        try {
            return Registry.get(Storage.class).createInputStream(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new ByteArrayInputStream(new byte[0]);
        }
    }

}
