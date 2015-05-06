package fake;

import com.codename1.io.Storage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class FakeStorage
    extends Storage
{
    public Set<String> names = new HashSet<String>();
    public ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[0]);
    public ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
    /**
     * Creates an input stream to the given storage source file
     *
     * @param name the name of the source file
     * @return the input stream
     * @throws java.io.IOException
     */
    @Override
    public InputStream createInputStream(String name) throws IOException {
        return inputStream;
    }

    /**
     * Creates an output stream to the storage with the given name
     *
     * @param name the storage file name
     * @return an output stream of limited capacity
     * @throws java.io.IOException
     */
    @Override
    public OutputStream createOutputStream(String name) throws IOException {
        names.add(name);
        return outputStream;
    }

    @Override
    public boolean exists(String name) {
        return names.contains(name);
    }
}
