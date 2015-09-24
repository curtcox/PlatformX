package x.io;

import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;

import java.io.*;

/**
 * Static methods that are useful for IO.
 */
public final class IO {

    public static String stringOrEmptyFrom(InputStream stream) {
        try {
            return stringFrom(stream);
        } catch (IOException e) {
            log(e);
            return "";
        }
    }

    public static String stringOrEmptyFrom(Reader reader) {
        try {
            return stringFrom(reader);
        } catch (IOException e) {
            log(e);
            return "";
        }
    }

    private static String stringFrom(InputStream stream) throws IOException {
        return new String(bytesFrom(stream));
    }

    private static String stringFrom(Reader reader) throws IOException {
        StringWriter buffer = new StringWriter();

        int numberRead;
        char[] data = new char[16384];

        while ((numberRead = reader.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, numberRead);
        }

        buffer.flush();

        return buffer.toString();
    }

    private static byte[] bytesFrom(InputStream stream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int numberRead;
        byte[] data = new byte[16384];

        while ((numberRead = stream.read(data, 0, data.length)) != -1) {
          buffer.write(data, 0, numberRead);
        }

        buffer.flush();

        return buffer.toByteArray();
    }

    private static void log(Exception e) {
        getLog().log(e);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IO.class);
    }

}
