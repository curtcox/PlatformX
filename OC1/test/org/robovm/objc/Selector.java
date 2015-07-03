package org.robovm.objc;

import org.robovm.rt.bro.NativeObject;

import java.util.HashMap;
import java.util.Map;

public final class Selector extends NativeObject {

    private static final Map<String, Selector> selectors = new HashMap();
    private final String name;

    private Selector(String name) {
        this.name = name;
    }

    public String getName() {
//        return ObjCRuntime.sel_getName(this).toStringAsciiZ();
        return name;
    }

    public String toString() {
        return this.getName();
    }

    public static Selector register(String name) {
        if(name == null) {
            throw new NullPointerException("name");
        }
        synchronized(selectors) {
            Selector sel = selectors.get(name);
            if (sel == null) {
                sel = new Selector(name);
//                    sel = ObjCRuntime.sel_registerName(BytePtr.toBytePtrAsciiZ(name));
//                if(sel == null) {
//                    throw new IllegalStateException("Objective-C failed to register selector \'" + name + "\'");
//                }

                selectors.put(name, sel);
            }

            return sel;
        }
    }
}
