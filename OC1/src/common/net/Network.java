package common.net;

import com.codename1.ui.Image;
import java.io.InputStream;
import java.net.URI;

/**
 * Network interface.
 * Interface for getting things from the network.
 * @author Curt
 */
public interface Network {

    /**
     * Return the data from the requested URI, or an empty stream, if something
     * went wrong.
     */
    InputStream getStreamFor(URI uri);

    Image getImage(URI uri);

    Image getImage(URI uri, int w, int h);
}
