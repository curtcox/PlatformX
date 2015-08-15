package x;

import x.log.ILog;
import x.log.ILogManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class IO {

    public static String stringOrEmptyFrom(InputStream stream) {
        try {
            return stringFrom(stream);
        } catch (IOException e) {
            log(e);
            return "";
        }
    }

    private static String stringFrom(InputStream stream) throws IOException {
        return new String(bytesFrom(stream));
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
