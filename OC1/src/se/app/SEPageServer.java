package se.app;

import mite.MiteHTTPServer;
import x.app.Registry;
import x.util.StringMap;

import java.io.IOException;

/**
 * A web server for publishing pages.
 */
final class SEPageServer {

    private static void startServer() throws IOException {
        new MiteHTTPServer(findEmptyPort(), new StringMapRequestHandler(stringMap()));
    }

    private static int findEmptyPort() {
        return 8000;
    }

    static void tryToStartServer() {
        try {
            startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static StringMap stringMap() {
        return Registry.get(StringMap.class);
    }

}
