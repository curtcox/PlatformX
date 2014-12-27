package j2se;

import com.codename1.ui.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import common.net.Network;
import c1.ui.EmptyImage;

/**
 * Testing network implementation that uses J2SE.
 * @author Curt
 */
public class J2seNetwork
    implements Network
{

    public InputStream getStreamFor(URI uri) {
        try {
            return uri.toURL().openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getImage(URI uri) {
        return new EmptyImage(0,0);
    }

    public Image getImage(URI uri, int w, int h) {
        return new EmptyImage(0,0);
    }
    
}
