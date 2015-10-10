package se.storage;

import x.stores.XStorage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public final class SERamStorage
    implements XStorage
{
    Map<String,Boolean> streams = new HashMap<String,Boolean>();

    @Override
    public OutputStream createOutputStream(String name) throws IOException {
        streams.put(name,true);
        return new ByteArrayOutputStream();
    }

    @Override
    public InputStream createInputStream(String name) throws IOException {
        return new ByteArrayInputStream(new byte[0]);
    }

    @Override
    public boolean exists(String name) {
        return streams.containsKey(name);
    }
}
