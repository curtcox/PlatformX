package c1.app;

import c1.device.C1DeviceInfo;
import c1.net.C1CachedNetwork;
import c1.pagefactories.C1ItemListPageFactoryFactory;
import c1.services.C1Locations;
import c1.services.Geocoder;
import c1.services.ILocationManager;
import c1.services.LocationManager;
import c1.storage.C1Storage;
import c1.ui.C1Display;
import c1.ui.C1FormFactory;
import c1.ui.C1Icons;
import com.codename1.ui.Display;
import x.Registry;
import x.app.CurrentState;
import x.device.IDeviceInfo;
import x.domain.ServiceProvider;
import x.log.ILogManager;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.net.Network;
import x.net.RootStringMap;
import x.page.PageFactory;
import x.page.RootPageFactory;
import x.page.dynamic.StringMapAsTaggedStringSources;
import x.page.dynamic.TaggedStringSources;
import x.pagefactories.ItemListPageFactoryFactory;
import x.services.ServiceProviders;
import x.stores.MyRatings;
import x.stores.XStorage;
import x.ui.IDisplay;
import x.ui.IFormFactory;
import x.util.StringMap;

/**
 * Loads the registry with all of the instances needed for startup.
 */
final class C1RegistryLoader {

    static void load() {
        loadLogging();
        loadCodenameOnePlatform();
        loadPlatform();
    }

    static void loadLogging() {
        put(C1ExceptionLogger.class, new C1ExceptionLogger());
        put(ILogManager.class,       new XLogManager());
        put(XLogWriter.class,        new XLogWriter());
    }
    
    static void loadCodenameOnePlatform() {
        put(IDisplay.class,         new C1Display());
        put(XStorage.class,         new C1Storage());
        put(Network.class,          new C1CachedNetwork());
        put(Display.class,          Display.getInstance());
        put(ILocationManager.class, new LocationManager());
        put(IDeviceInfo.class,      new C1DeviceInfo());
    }

    static void loadPlatform() {
        put(IFormFactory.class,     new C1FormFactory());
        put(MyRatings.class,        new MyRatings());
        put(C1Locations.class,      new C1Locations());
        put(ServiceProvider.class,  ServiceProvider.NULL);
        put(ServiceProviders.class, new ServiceProviders());
        put(Geocoder.class,         new Geocoder());
        put(CurrentState.class,     new CurrentState());
        put(C1Icons.class,          new C1Icons());
        StringMap stringMap = RootStringMap.of();
        put(StringMap.class,        stringMap);
        put(TaggedStringSources.class,        new StringMapAsTaggedStringSources(stringMap));
        put(ItemListPageFactoryFactory.class, new C1ItemListPageFactoryFactory());
        put(PageFactory.class,      RootPageFactory.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
