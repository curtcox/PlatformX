package se.ref;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

final class SEReferenceIntrospector {

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
