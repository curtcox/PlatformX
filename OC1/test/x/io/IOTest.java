package x.io;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.IO;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static mach.Mocks._;
import static mach.Mocks.verify;
import static org.junit.Assert.assertEquals;

public class IOTest {

    ILog log;
    ILogManager logManager;

    @Before
    public void setUp() {
        Mocks.init(this);
        _(log); logManager.getLog(IO.class);
        Registry.put(ILogManager.class,logManager);
    }

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
        final IOException e = new IOException();
        InputStream stream = new ErrorThrowingInputStream(e);
        _(); log.log(e);

        String actual = IO.stringOrEmptyFrom(stream);

        assertEquals(expected,actual);
    }

    @Test
    public void stringOrEmptyFrom_logs_exception_when_stream_throws_exception() {
        final IOException e = new IOException();
        InputStream stream = new ErrorThrowingInputStream(e);
        _(); log.log(e);

        IO.stringOrEmptyFrom(stream);

        verify();
        log.log(e);
    }

    static class ErrorThrowingInputStream extends InputStream {
        final IOException e;

        ErrorThrowingInputStream(IOException e) {
            this.e = e;
        }
        @Override
        public int read() throws IOException {
            throw e;
        }
    }
}
