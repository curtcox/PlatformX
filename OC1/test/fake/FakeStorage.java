package fake;

import com.codename1.io.Storage;
import com.codename1.io.Util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Curt
 */
public class FakeStorage 
    extends Storage
{
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
    public OutputStream createOutputStream(String name) throws IOException {
        return outputStream;
    }

}
