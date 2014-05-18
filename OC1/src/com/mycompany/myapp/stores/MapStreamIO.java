package com.mycompany.myapp.stores;

import com.codename1.io.Storage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Curt
 */
public final class MapStreamIO {

    private final Storage storage;
    private final String file; 
    
    MapStreamIO(Storage storage, String file) {
        this.storage = storage;
        this.file = file;
    }
    
    private OutputStream toStorage() throws IOException {
        return storage.createOutputStream(file);
    }

    private InputStream fromStorage() throws IOException {
        return storage.createInputStream(file);
    }

    Map<String, String> readMap() {
        Map map = new HashMap();
        try {
            readMap(map,new DataInputStream(fromStorage()));
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return map;
    }

    void writeMap(Map<String, String> map) {
        try {
            writeMap(map,new DataOutputStream(toStorage()));
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    void writeMap(Map<String, String> map, DataOutputStream data) throws IOException {
        write(data,"Map");
        write(data,"size=");
        write(data,map.size());
        write(data,"keys=values");
        for (String key : map.keySet()) {
            write(data,key + "=" + map.get(key));
        }
        write(data,"end");
        data.close();
    }

    private void readMap(Map map, DataInputStream data) throws IOException {
        read(data,"Map");
        read(data,"size=");
        int size = readInt(data);
        read(data,"keys=values");
        for (int i=0; i<size; i++) {
            String pair = readString(data);
            String[] parts = split(pair);
            map.put(parts[0],parts[1]);
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

    private String[] split(String pair) {
        int at = pair.indexOf("=");
        String key = pair.substring(0,at);
        String value = pair.substring(at+1, pair.length());
        return new String[] {key,value};
    }

}
