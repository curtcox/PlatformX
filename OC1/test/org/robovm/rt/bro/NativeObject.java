//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.robovm.rt.bro;

import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.MarshalsPointer;

@org.robovm.rt.bro.annotation.Marshaler(NativeObject.Marshaler.class)
public abstract class NativeObject {
    private long handle;

    protected NativeObject() {
    }

    public final long getHandle() {
        return this.handle;
    }

    protected final void setHandle(long handle) {
        this.handle = handle;
    }

    public <U extends NativeObject> U as(Class<U> type) {
        return (U) this;
    }

    public static class Marshaler {
        public Marshaler() {}

        @MarshalsPointer
        public static NativeObject toObject(Class<?> cls, long handle, long flags) {
            if(handle == 0L) {
                return null;
            } else {
                NativeObject o = (NativeObject)VM.allocateObject(cls);
                o.setHandle(handle);
                return o;
            }
        }

        @MarshalsPointer
        public static long toNative(NativeObject o, long flags) {
            return o == null?0L:o.getHandle();
        }
    }
}
