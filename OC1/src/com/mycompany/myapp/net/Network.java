package com.mycompany.myapp.net;

import java.io.InputStream;

/**
 *
 * @author Curt
 */
public interface Network {

    InputStream getStreamFor(String url);

}
