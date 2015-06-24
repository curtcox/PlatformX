package se.app;

import mite.HTTPRequest;
import mite.handlers.AbstractRequestHandler;
import x.util.StringMap;

import java.io.IOException;

/**
 * A StringMap as a HTTPRequestHandler
 */
final class StringMapRequestHandler
    extends AbstractRequestHandler
{
    final StringMap map;

    public StringMapRequestHandler(StringMap map) {
        this.map = map;
    }

    @Override
    protected boolean handles(HTTPRequest request) {
        return map.get(request.filename) != null;
    }

    @Override
    protected String handle(HTTPRequest request) throws IOException {
        return map.get(request.filename);
    }

}
