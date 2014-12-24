package se1.app;

import oc1.app.Registry;
import oc1.ui.IDisplay;
import oc1.ui.IFormFactory;
import se1.ui.SEDisplay;
import se1.ui.SEFormFactory;

/**
 * Loads the registry with all of the instances needed for startup.
 * @author Curt
 */
final class RegistryLoader {

    static void load() {
        put(IFormFactory.class,     new SEFormFactory());
        put(IDisplay.class,         SEDisplay.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
