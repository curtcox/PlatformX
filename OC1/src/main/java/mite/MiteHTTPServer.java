package mite;

import mite.handlers.CompositeRequestHandler;
import mite.handlers.EchoRequestHandler;
import mite.handlers.UnsupportedRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Opens a server socket and hands off any requests to another thread.
 */
public final class MiteHTTPServer
    extends Thread
{

    private final ServerSocket server;
    private final RequestHandler handler;
    private final Executor executor = Executors.newFixedThreadPool(3);
    static final String NAME = "MiteHTTPServer 0.1";

    public MiteHTTPServer(int port, RequestHandler handler)
        throws IOException
    {
        this.server = new ServerSocket(port);
        this.handler = handler;
    }

    private static void startListeningOnPort(int port) throws IOException {
        log("Accepting connections on port " + port);
        RequestHandler handler = CompositeRequestHandler.of(EchoRequestHandler.of(), UnsupportedRequestHandler.of());
        MiteHTTPServer server = new MiteHTTPServer(port,handler);
        server.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                executor.execute(new RequestProcessor(server.accept(),handler));
            } catch (IOException e) {
                log(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        startListeningOnPort(8000);
    }

    private static void log(Throwable t) {
        t.printStackTrace();
    }

    private static void log(String message) {
        System.out.println("MiteHTTPServer : " + message);
    }

}
