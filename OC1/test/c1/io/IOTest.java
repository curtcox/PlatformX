package c1.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import common.IO;
import org.junit.Test;
import static org.junit.Assert.*;

public class IOTest {
    
    @Test
    public void stringOrEmptyFrom_returns_expected_string() {
        String expected = "Stuff in the string";
        InputStream stream = new ByteArrayInputStream(expected.getBytes());
        
        String actual = IO.stringOrEmptyFrom(stream);
        
        assertEquals(expected,actual);
    }

    @Test
    public void stringOrEmptyFrom_returns_empty_when_stream_throws_exception() {
        String expected = "";
        InputStream stream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }
        };

        String actual = IO.stringOrEmptyFrom(stream);

        assertEquals(expected,actual);
    }

}
