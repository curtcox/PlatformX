package se.ref;

import x.app.Registry;

import java.util.*;

public final class References {

    Map<Object,Object> refs = new HashMap<>();
    private static References singleton = new References();

    /** Use the factory */
    private References() {}

    public static References of() {
        return singleton;
    }

    public Object[] to(Object object) {
        return refs.get(object)==null
            ? new Object[0]
            : new Object[] { refs.get(object)};
    }

    public void noteObjectReferences(Object ref, Object object) {
        refs.put(object,ref);
    }

}
