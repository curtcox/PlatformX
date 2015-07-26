package c1.storage;

import com.codename1.io.Storage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import x.stores.XStorage;

public final class C1Storage 
    implements XStorage
{
    private final Storage storage;
    
    public C1Storage() {
        storage = new Storage();
    }

    public OutputStream createOutputStream(String name) throws IOException {
        return storage.createOutputStream(name);
    }

    public InputStream createInputStream(String name) throws IOException {
        return storage.createInputStream(name);
    }

    public boolean exists(String name) {
        return storage.exists(name);
    }
    
}
