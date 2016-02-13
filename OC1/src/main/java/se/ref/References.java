package se.ref;

import java.util.*;

public class References {

    Map<Object,Object> refs = new HashMap<>();

    public Object[] to(Object object) {
        return refs.get(object)==null
            ? new Object[0]
            : new Object[] { refs.get(object)};
    }

    public void noteObjectReferences(Object ref, Object object) {
        refs.put(object,ref);
    }
}
