package mite.handlers;

import mite.ContentType;
import mite.HTTPVersion;
import mite.RequestHandler;
import mite.StatusCode;

import java.io.*;
import java.net.Socket;

/**
 * To report to the client that the request is unsupported.
 */
public final class UnsupportedRequestHandler
    implements RequestHandler
{

    public static UnsupportedRequestHandler of() {
        return new UnsupportedRequestHandler();
    }

    private UnsupportedRequestHandler() {}

    private static final String NOT_IMPLEMENTED_PAGE =
            "<HTML>" +
                    "<HEAD> <TITLE>Not Implemented</TITLE> </HEAD>" +
                    "<BODY> <H1>HTTP Error 501: Not Implemented</H1> </BODY>" +
            "</HTML>";

    public void handle(String request, Socket socket, InputStream in, OutputStream out)
        throws IOException
    {
        Writer writer = new OutputStreamWriter(out);
        String page = NOT_IMPLEMENTED_PAGE;
        if (HTTPVersion.isMIMEAware(request)) {
            ContentType.HTML.writeMIMEHeader(writer, StatusCode.NOT_IMPLEMENTED, page.length());
        }
        writer.write(page);
        writer.close();
    }

    public boolean handles(String request) {
        return true;
    }

}
