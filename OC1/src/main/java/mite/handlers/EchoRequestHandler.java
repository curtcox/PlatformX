package mite.handlers;

import mite.HTTPRequest;

import java.io.IOException;

/**
 * Simple handler mostly for demonstration and debugging.
 */
public final class EchoRequestHandler
    extends AbstractRequestHandler
{

    public static EchoRequestHandler of() {
        return new EchoRequestHandler();
    }

    private EchoRequestHandler() {}

    protected String handle(HTTPRequest request) throws IOException {
        String R = "\r";
        return "<html>" +
                  "<body>" +
                      "<pre>" +
                          "request =" + request          + R +
                          "method  =" + request.method   + R +
                          "filename=" + request.filename + R +
                      "</pre>" +
                  "</body>" +
               "</html>";
    }

    protected boolean handles(HTTPRequest request) {
        return true;
    }

}
