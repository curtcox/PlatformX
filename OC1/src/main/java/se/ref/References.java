package se.ref;

import java.lang.reflect.Field;
import java.util.*;

public final class References {

    List referencing = new ArrayList();
    private static References singleton = new References();

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
        List references = new ArrayList();
        for (Field field :object.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                references.add(field.get(object));
            } catch (IllegalAccessException e) {

            }
        }
        return references;
    }
}
