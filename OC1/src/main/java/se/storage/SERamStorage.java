package se.storage;

import x.stores.XStorage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public final class SERamStorage
    implements XStorage
{
    Map<String,ByteArrayOutputStream> streams = new HashMap<String,ByteArrayOutputStream>();

    @Override
    public OutputStream createOutputStream(String name) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        streams.put(name,outputStream);
        return outputStream;
    }

    @Override
    public InputStream createInputStream(String name) throws IOException {
        return new ByteArrayInputStream(streams.get(name).toByteArray());
    }

    @Override
    public boolean exists(String name) {
        return streams.containsKey(name);
    }
}
