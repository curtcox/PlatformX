package se.app;

import common.log.ILogManager;
import common.Registry;
import common.net.Network;
import se.log.*;
import common.screen.ScreenFactory;
import c1.ui.IDisplay;
import c1.ui.IFormFactory;
import common.util.StringMap;
import common.net.RootStringMap;
import se.net.SERawNetwork;
import se.screen.RootScreenFactory;
import se.ui.SEDisplay;
import se.ui.SEFormFactory;

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
        put(Network.class,          new SERawNetwork());
        put(ScreenFactory.class,    RootScreenFactory.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
