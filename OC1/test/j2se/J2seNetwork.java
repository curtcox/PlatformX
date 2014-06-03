package j2se;

import com.mycompany.myapp.net.Network;
import java.io.InputStream;
import java.net.URL;

/**
 * Testing network implementation that uses J2SE.
 * @author Curt
 */
public class J2seNetwork
    implements Network
{

    public InputStream getStreamFor(String urlString) {
        try {
            URL url = new URL(urlString);
            return url.openStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
