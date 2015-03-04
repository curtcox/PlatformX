package se.app;

import common.util.StringMap;
import mite.HTTPRequest;
import mite.handlers.AbstractRequestHandler;

import java.io.IOException;

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
        return null;
    }

}
