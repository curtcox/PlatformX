package se.ref;

import java.util.*;

public final class References {

    final List referencing = new ArrayList();
    final ReferenceIntrospector referenceIntrospector = new ReferenceIntrospector();
    private static final References singleton = new References();

    /** Use the factory */
    private References() {}

    public static References of() {
        return singleton;
    }

    public Object[] to(Object object) {
        List referencers = new ArrayList();
        for (Object ref : referencing) {
            if (referencesFrom(ref).contains(object)) {
                referencers.add(ref);
            }
        }
        return referencers.toArray();
    }

    public void noteObject(Object object) {
        referencing.add(object);
    }

    public Object[] from(Object object) {
        return referencesFrom(object).toArray();
    }

    List referencesFrom(Object object) {
        return referenceIntrospector.referencesFrom(object);
    }
}
