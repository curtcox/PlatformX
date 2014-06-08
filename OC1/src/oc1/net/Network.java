package oc1.net;

import com.codename1.ui.Image;
import java.io.InputStream;
import java.net.URI;

/**
 *
 * @author Curt
 */
public interface Network {

    InputStream getStreamFor(URI uri);

    Image getImage(URI uri);
}
