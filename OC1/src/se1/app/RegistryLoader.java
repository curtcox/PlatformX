package se1.app;

import common.log.ILogManager;
import common.Registry;
import se1.log.*;
import oc1.screen.ScreenFactory;
import oc1.ui.IDisplay;
import oc1.ui.IFormFactory;
import oc1.util.StringMap;
import oc2.net.RootStringMap;
import se2.screen.RootScreenFactory;
import se1.ui.SEDisplay;
import se1.ui.SEFormFactory;

/**
 * Loads the registry with all of the instances needed for startup.
 * @author Curt
 */
final class RegistryLoader {

    static void load() {
        put(ILogManager.class,      new LogManager());
        put(LogWriter.class,        new LogWriter());
        put(IFormFactory.class,     new SEFormFactory());
        put(IDisplay.class,         SEDisplay.of());
        put(StringMap.class,        RootStringMap.of());
        put(ScreenFactory.class,    RootScreenFactory.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
