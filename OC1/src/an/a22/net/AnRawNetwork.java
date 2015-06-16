package an.a22.net;

import common.net.Network;
import common.uiwidget.UIImage;

import java.io.InputStream;
import java.net.URI;

public final class AnRawNetwork
    implements Network
{
    @Override
    public InputStream getStreamFor(URI uri) {
        return null;
    }

    @Override
    public UIImage getImage(URI uri) {
        return null;
    }

    @Override
    public UIImage getImage(URI uri, int w, int h) {
        return null;
    }
}
