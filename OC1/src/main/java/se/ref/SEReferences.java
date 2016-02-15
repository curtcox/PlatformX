package se.ref;

import java.util.*;

public final class SEReferences {

    final List referencing = new ArrayList();
    final SEReferenceIntrospector referenceIntrospector = new SEReferenceIntrospector();
    private static final SEReferences singleton = new SEReferences();

    /** Use the factory */
    private SEReferences() {}

    public static SEReferences of() {
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
