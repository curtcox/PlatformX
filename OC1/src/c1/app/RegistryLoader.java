package c1.app;

import com.codename1.io.Storage;
import com.codename1.ui.Display;
import common.Registry;
import common.app.CurrentState;
import common.domain.ServiceProvider;
import common.net.Network;
import common.net.RootStringMap;
import common.screen.ScreenFactory;
import c1.log.*;
import c1.net.*;
import c1.screen.RootScreenFactory;
import c1.services.*;
import c1.ui.*;
import common.log.ILogManager;
import common.screen.dynamic.StringMapAsTaggedStringSources;
import common.screen.dynamic.TaggedStringSources;
import common.ui.IDisplay;
import common.ui.IFormFactory;
import common.util.*;
import common.stores.MyRatings;

/**
 * Loads the registry with all of the instances needed for startup.
 */
final class RegistryLoader {

    static void load() {
        loadCodenameOnePlatform();
        loadPlatform();
    }

    static void loadCodenameOnePlatform() {
        put(Storage.class,          new Storage());
        put(Network.class,          new CachedNetwork());
        put(IDisplay.class,         new C1Display());
        put(Display.class,          Display.getInstance());
        put(ILocationManager.class, new LocationManager());
    }

    static void loadPlatform() {
        put(ExceptionLogger.class,  new ExceptionLogger());
        put(ILogManager.class,      new LogManager());
        put(LogWriter.class,        new LogWriter());
        put(IFormFactory.class,     new C1FormFactory());
        put(MyRatings.class,        new MyRatings());
        put(Locations.class,        new Locations());
        put(ServiceProvider.class,  ServiceProvider.NULL);
        put(ServiceProviders.class, new ServiceProviders());
        put(Geocoder.class,         new Geocoder());
        put(CurrentState.class,     new CurrentState());
        put(Icons.class,            new Icons());
        StringMap stringMap = RootStringMap.of();
        put(StringMap.class,        stringMap);
        put(TaggedStringSources.class, new StringMapAsTaggedStringSources(stringMap));
        put(ScreenFactory.class,    RootScreenFactory.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
