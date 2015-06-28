package x.net;

import x.uiwidget.XImage;

import java.io.InputStream;
import java.net.URI;

/**
 * Network interface.
 * Interface for getting things from the network.
 */
public interface Network {

    /**
     * Return the data from the requested URI, or an empty stream, if something
     * went wrong.
     */
    InputStream getStreamFor(URI uri);

    XImage getImage(URI uri);

    XImage getImage(URI uri, int w, int h);
}
