package c1.app;

import c1.device.C1DeviceInfo;
import c1.screenfactories.C1ItemListScreenFactoryFactory;
import com.codename1.io.Storage;
import com.codename1.ui.Display;
import common.Registry;
import common.app.CurrentState;
import common.device.IDeviceInfo;
import common.domain.ServiceProvider;
import common.net.Network;
import common.net.RootStringMap;
import common.screen.RootScreenFactory;
import common.screen.ScreenFactory;
import c1.log.*;
import c1.net.*;
import c1.services.*;
import c1.ui.*;
import common.log.ILogManager;
import common.screen.dynamic.StringMapAsTaggedStringSources;
import common.screen.dynamic.TaggedStringSources;
import common.screenfactories.ItemListScreenFactoryFactory;
import common.services.ServiceProviders;
import common.ui.IDisplay;
import common.ui.IFormFactory;
import common.util.*;
import common.stores.MyRatings;

/**
 * Loads the registry with all of the instances needed for startup.
 */
final class C1RegistryLoader {

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
        put(IDeviceInfo.class,      new C1DeviceInfo());
    }

    static void loadPlatform() {
        put(ExceptionLogger.class,  new ExceptionLogger());
        put(ILogManager.class,      new LogManager());
        put(LogWriter.class,        new LogWriter());
        put(IFormFactory.class,     new C1FormFactory());
        put(MyRatings.class,        new MyRatings());
        put(C1Locations.class,        new C1Locations());
        put(ServiceProvider.class,  ServiceProvider.NULL);
        put(ServiceProviders.class, new ServiceProviders());
        put(Geocoder.class,         new Geocoder());
        put(CurrentState.class,     new CurrentState());
        put(Icons.class,            new Icons());
        StringMap stringMap = RootStringMap.of();
        put(StringMap.class,        stringMap);
        put(TaggedStringSources.class, new StringMapAsTaggedStringSources(stringMap));
        put(ItemListScreenFactoryFactory.class, new C1ItemListScreenFactoryFactory());
        put(ScreenFactory.class,    RootScreenFactory.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
