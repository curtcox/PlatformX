package com.mycompany.fake;

import com.codename1.io.Storage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Curt
 */
public class FakeStorage 
    extends Storage
{
        /**
     * Creates an input stream to the given storage source file
     *
     * @param name the name of the source file
     * @return the input stream
     */
    @Override
    public InputStream createInputStream(String name) throws IOException {
        return new ByteArrayInputStream(new byte[0]);
    }

}
