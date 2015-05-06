package mite;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Processes a simple HTTP request.
 * This class contains just enough logic to determine who to hand the request to.
 */
final class RequestProcessor
    implements Runnable
{

    private final Socket connection;
    private final InputStream in;
    private final OutputStream out;

    /**
     * This will really handle the request.
     */
    private final RequestHandler handler;

    public RequestProcessor(Socket connection, RequestHandler handler) throws IOException {
        this.connection = connection;
        this.handler    = handler;
        out    = connection.getOutputStream();
        in     = connection.getInputStream();
    }

    public void run() {
        try {
            handleRequest();
        } catch (IOException e) {
            log(e);
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                log(e);
            }
        }
    }

    private void handleRequest() throws IOException {
        String request = readRequest();
        log(request);
        if (handler.handles(request)) {
            handler.handle(request,connection,in,out);
            return;
        }
        throw new UnsupportedOperationException(request);
    }

    private String readRequest() throws IOException {
        final StringBuilder requestLine = new StringBuilder();
        int max_bytes_in_request = 1024;
        for (int i=0; i<max_bytes_in_request; i++) {
            int c = in.read();
            if (c == '\r' || c == '\n') break;
            requestLine.append((char) c);
        }

        return requestLine.toString();
    }

    private static void log(Throwable t) {
        t.printStackTrace();
    }

    private static void log(String message) {
        System.out.println("RequestProcessor : " + message);
    }

}
