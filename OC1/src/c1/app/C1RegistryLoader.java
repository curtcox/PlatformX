package c1.app;

import c1.device.C1DeviceInfo;
import c1.event.C1SwappableListFactory;
import c1.net.C1CachedNetwork;
import c1.services.C1Locations;
import c1.services.Geocoder;
import c1.services.ILocationManager;
import c1.services.LocationManager;
import c1.storage.C1Storage;
import c1.ui.C1Display;
import c1.ui.C1FormFactory;
import c1.ui.C1Icons;
import c1.uilist.C1SearchableListFactory;
import com.codename1.ui.Display;
import x.app.CurrentState;
import x.app.Registry;
import x.app.RootPageFactory;
import x.device.XDeviceInfo;
import x.domain.ServiceProvider;
import x.event.SwappableList;
import x.log.ILogManager;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.net.Network;
import x.net.RootStringMap;
import x.page.PageFactory;
import x.page.dynamic.StringMapAsTaggedStringSources;
import x.page.dynamic.TaggedStringSources;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.XItemListPageFactoryFactory;
import x.services.LocationService;
import x.services.ServiceProviders;
import x.stores.MyRatings;
import x.stores.XStorage;
import x.ui.IDisplay;
import x.ui.IFormFactory;
import x.uiwidget.XSearchableList;
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
        put(XDeviceInfo.class,      new C1DeviceInfo());
    }

    static void loadPlatform() {
        put(IFormFactory.class,            new C1FormFactory());
        put(MyRatings.class,               new MyRatings());
        put(LocationService.class,         new C1Locations());
        put(XSearchableList.Factory.class, new C1SearchableListFactory());
        put(ServiceProvider.class,         ServiceProvider.NULL);
        put(ServiceProviders.class,        new ServiceProviders());
        put(Geocoder.class,                new Geocoder());
        put(CurrentState.class,            new CurrentState());
        put(C1Icons.class,                 new C1Icons());
        StringMap stringMap = RootStringMap.of();
        put(StringMap.class,                  stringMap);
        put(TaggedStringSources.class,        new StringMapAsTaggedStringSources(stringMap));
        put(ItemListPageFactoryFactory.class, new XItemListPageFactoryFactory());
        put(SwappableList.Factory.class,      new C1SwappableListFactory());
        put(PageFactory.class,                RootPageFactory.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
