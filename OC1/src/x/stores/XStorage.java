package x.stores;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface XStorage {
    OutputStream createOutputStream(String name) throws IOException;
    InputStream createInputStream(String name) throws IOException;
    boolean exists(String name);
}
