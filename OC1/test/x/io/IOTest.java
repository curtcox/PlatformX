package x.io;

import config.ShouldRun;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;

import java.io.*;

import static mach.Mocks._;
import static mach.Mocks.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

public class IOTest {

    ILog log;
    ILogManager logManager;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
        Mocks.init(this);
        _(log); logManager.getLog(null,IO.class);
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
    public void stringOrEmptyFromReader_returns_expected_string() {
        String expected = "Stuff in the string";
        InputStream stream = new ByteArrayInputStream(expected.getBytes());
        Reader reader = new InputStreamReader(stream);

        String actual = IO.stringOrEmptyFrom(reader);

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
    public void stringOrEmptyFromReader_returns_empty_when_stream_throws_exception() {
        String expected = "";
        final IOException e = new IOException();
        Reader reader = new ErrorThrowingReader(e);
        _(); log.log(e);

        String actual = IO.stringOrEmptyFrom(reader);

        assertEquals(expected,actual);
    }

    @Test
    public void write_writes_string_to_the_given_stream() throws IOException {
        String expected = "Stuff in the string";
        ByteArrayOutputStream written = new ByteArrayOutputStream();
        IO.write(expected,written);
        InputStream in = new ByteArrayInputStream(written.toByteArray());

        String actual = IO.stringOrEmptyFrom(in);

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

    static class ErrorThrowingReader extends Reader {
        final IOException e;

        ErrorThrowingReader(IOException e) {
            this.e = e;
        }

        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            throw e;
        }

        @Override
        public void close() throws IOException {
            throw e;
        }
    }

}
