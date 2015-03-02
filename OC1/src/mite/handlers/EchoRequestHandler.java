package mite.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.StringTokenizer;

import mite.ContentType;
import mite.HTTPVersion;
import mite.RequestHandler;
import mite.StatusCode;

/**
 * Simple handler mostly for demonstration and debugging.
 */
public final class EchoRequestHandler
    implements RequestHandler
{

    public static EchoRequestHandler of() {
        return new EchoRequestHandler();
    }

    private EchoRequestHandler() {}

    public void handle(String request, Socket socket, InputStream in, OutputStream out) throws IOException {
        StringTokenizer st = new StringTokenizer(request);
        String method = st.nextToken();
        String filename = st.nextToken();
        Writer writer = new OutputStreamWriter(out);
        String page = page(request,method,filename);
        if (HTTPVersion.isMIMEAware(request)) {
            ContentType.HTML.writeMIMEHeader(writer, StatusCode.OK, page.length());
        }
        writer.write(page);
        writer.close();
        socket.close();
    }

    String page(String request, String method, String filename) throws IOException {
        String R = "\r";
        return "<html>" +
                  "<body>" +
                      "<pre>" +
                          "request =" + request  + R +
                          "method  =" + method   + R +
                          "filename=" + filename + R +
                      "</pre>" +
                  "</body>" +
               "</html>";
    }

    public boolean handles(String request) {
        return true;
    }

}
