package j2se;

import com.codename1.ui.Image;
import myapp.net.Network;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
