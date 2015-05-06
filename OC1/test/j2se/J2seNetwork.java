package j2se;

import java.io.*;
import java.net.URI;
import common.net.Network;
import common.uiwidget.UIImage;

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

    public UIImage getImage(URI uri) {
        return new UIImage();
    }

    public UIImage getImage(URI uri, int w, int h) {
        return new UIImage();
    }
    
}
