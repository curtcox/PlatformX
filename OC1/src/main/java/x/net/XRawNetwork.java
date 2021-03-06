package x.net;

import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uiwidget.XImage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public final class XRawNetwork
    implements Network
{
    @Override
    public InputStream getStreamFor(URI uri) {
        try {
            return uri.toURL().openStream();
        } catch (IOException e) {
            log(e);
            return new ByteArrayInputStream(new byte[0]);
        }
    }

    @Override
    public XImage getImage(URI uri) {
        return null;
    }

    @Override
    public XImage getImage(URI uri, int w, int h) {
        return null;
    }

    private void log(Exception e) {
        getLog().log(e);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(this);
    }

}
