package se.ref;

import java.util.*;

public final class References {

    Map<Object,Object> targetToSource = new HashMap<>();
    Map<Object,Object> sourceToTarget = new HashMap<>();
    private static References singleton = new References();

    /** Use the factory */
    private References() {}

    public static References of() {
        return singleton;
    }

    public Object[] to(Object object) {
        return targetToSource.get(object)==null
            ? new Object[0]
            : new Object[] { targetToSource.get(object)};
    }

    public void noteObjectReferences(Object referencing, Object referenced) {
        sourceToTarget.put(referencing,referenced);
        targetToSource.put(referenced,referencing);
    }

    public Object[] from(Object object) {
        return sourceToTarget.get(object)==null
                ? new Object[0]
                : new Object[] { sourceToTarget.get(object)};
    }
}
