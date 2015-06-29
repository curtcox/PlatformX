package j2se;

import java.io.*;
import java.net.URI;
import x.net.Network;
import x.uiwidget.XImage;

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

    public XImage getImage(URI uri) {
        return new XImage();
    }

    public XImage getImage(URI uri, int w, int h) {
        return new XImage();
    }
    
}
