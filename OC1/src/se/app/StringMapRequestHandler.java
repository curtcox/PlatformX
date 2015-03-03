package se.app;

import common.util.StringMap;
import mite.RequestHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

final class StringMapRequestHandler
    implements RequestHandler
{
    final StringMap map;

    public StringMapRequestHandler(StringMap map) {
        this.map = map;
    }

    @Override
    public boolean handles(String request) {
        return map.get(request) != null;
    }

    @Override
    public void handle(String request, Socket socket, InputStream in, OutputStream out) throws IOException {

    }
}
