package se.storage;

import org.junit.Test;
import x.io.IO;
import x.stores.XStorage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.*;

public class SERamStorageTest {

    SERamStorage storage = new SERamStorage();

    @Test
    public void can_create() {
        assertNotNull(new SERamStorage());
    }

    @Test
    public void is_XStorage() {
        assertTrue(new SERamStorage() instanceof XStorage);
    }

    @Test
    public void exist_returns_false_before_stream_is_created() {
        assertFalse(storage.exists("once"));
        assertFalse(storage.exists("twice"));
    }

    @Test
    public void exist_returns_true_after_stream_is_created() throws IOException {
        OutputStream stream = storage.createOutputStream("once");
        stream.close();
        assertTrue(storage.exists("once"));
    }

    @Test
    public void empty_data_written_to_stream_can_then_be_read_from_stream() throws IOException {
        OutputStream output = storage.createOutputStream("once");
        output.close();
        InputStream input = storage.createInputStream("once");
        assertEquals(-1, input.read());
    }

    @Test
    public void random_data_written_to_stream_can_then_be_read_from_stream() throws IOException {
        OutputStream output = storage.createOutputStream("random");
        String data = toString();
        IO.write(data,output);
        output.close();
        InputStream input = storage.createInputStream("random");
        assertEquals(data,IO.stringOrEmptyFrom(input));
    }

}