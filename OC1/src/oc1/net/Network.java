package oc1.net;

import com.codename1.ui.Image;
import java.io.InputStream;
import java.net.URI;

/**
 * Network interface.
 * Interface for getting things from the network.
 * @author Curt
 */
public interface Network {

    InputStream getStreamFor(URI uri);

    Image getImage(URI uri);
}
