package com.mycompany.myapp.net;

import com.codename1.io.Storage;
import com.codename1.io.Util;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 *
 * @author Curt
 */
public final class Network {
    
    private final Storage storage = new Storage();
    
    public InputStream getStreamFor(String url) {
        String fileName = "temp";
        boolean showProgress = true;
        //if (!Util.downloadUrlToStorage(url, fileName, showProgress)) {
        //    System.out.println("Download failed");            
        //}
        try {
            return storage.createInputStream(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new ByteArrayInputStream(new byte[0]);
        }
    }

}
