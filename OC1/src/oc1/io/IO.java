package oc1.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Curt
 */
public final class IO {

    public static String stringOrEmptyFrom(InputStream stream) {
        try {
            return stringFrom(stream);
        } catch (IOException e) {
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

}
