package se.storage;

import x.stores.XStorage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class SERamStorage
    implements XStorage
{
    @Override
    public OutputStream createOutputStream(String name) throws IOException {
        return null;
    }

    @Override
    public InputStream createInputStream(String name) throws IOException {
        return null;
    }

    @Override
    public boolean exists(String name) {
        return false;
    }
}
