package x.stores;

import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * For making maps persistent.
 */
public final class MapStorageIO<K,V> {

    private final PairIO<K,V> io;
    private final XStorage storage;
    private final String file; 
    
    public MapStorageIO(XStorage storage, PairIO<K,V> io, String file) {
        this.storage = storage;
        this.file = file;
        this.io = io;
    }
    
    private OutputStream toStorage() throws IOException {
        return storage.createOutputStream(file);
    }

    private InputStream fromStorage() throws IOException {
        return storage.createInputStream(file);
    }

    public Map<K, V> readMap() {
        Map<K,V> map = new HashMap();
        if (!storage.exists(file)) {
            log(file + " not found in storage");
            return map;
        }
        try {
            readMap(map,io,new DataInputStream(fromStorage()));
        } catch (IOException e) {
            log(e);
        } 
        return map;
    }

    public void writeMap(Map<K, V> map) {
        try {
            writeMap(map,new DataOutputStream(toStorage()));
        } catch (IOException e) {
            log(e);
        } 
    }

    private void writeMap(Map<K, V> map, DataOutputStream data) throws IOException {
        write(data,"Map");
        write(data,"size=");
        write(data,map.size());
        write(data,"keys=values");
        for (K key : map.keySet()) {
            write(data,io.writePair(key, map.get(key)));
        }
        write(data,"end");
        data.close();
    }

    private void readMap(Map<K,V> map, PairIO<K,V> io, DataInputStream data) throws IOException {
        read(data,"Map");
        read(data,"size=");
        int size = readInt(data);
        read(data,"keys=values");
        for (int i=0; i<size; i++) {
            String pair = readString(data);
            map.put(io.readKey(pair),io.readValue(pair));
        }
        read(data,"end");
        data.close();
    }

    private void write(DataOutputStream data, int value) throws IOException {
        data.writeInt(value);
    }

    private void write(DataOutputStream data, String string) throws IOException {
        data.writeUTF(string);
    }

    private void read(DataInputStream data, String expected) throws IOException {
        String actual = data.readUTF();
        if (!expected.equals(actual)) {
            throw new RuntimeException(expected + "!=" + actual);
        }
    }

    private int readInt(DataInputStream data) throws IOException {
        return data.readInt();
    }

    private String readString(DataInputStream data) throws IOException {
        String value = data.readUTF();
        return value;
    }

    private void log(Exception e) {
        getLog().log(e);    
    }

    private void log(String message) {
        getLog().log(message);    
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(this);
    }

}
