package an.a22.app;

import an.a22.device.AnDeviceInfo;
import an.a22.net.AnRawNetwork;
import an.a22.screenfactories.AnItemListScreenFactoryFactory;
import an.a22.ui.AnDisplay;
import an.a22.ui.AnFormFactory;
import common.Registry;
import common.app.CurrentState;
import common.device.IDeviceInfo;
import common.domain.ServiceProvider;
import common.log.CommonLogManager;
import common.log.CommonLogWriter;
import common.log.ILogManager;
import common.net.Network;
import common.net.RootStringMap;
import common.screen.RootScreenFactory;
import common.screen.ScreenFactory;
import common.screen.dynamic.StringMapAsTaggedStringSources;
import common.screen.dynamic.TaggedStringSources;
import common.screenfactories.ItemListScreenFactoryFactory;
import common.services.ServiceProviders;
import common.stores.MyRatings;
import common.ui.IDisplay;
import common.ui.IFormFactory;
import common.util.StringMap;

/**
 * Loads the registry with all of the instances needed for startup.
 */
final class AnRegistryLoader {

    static void load() {
        loadAndroidPlatform();
        loadPlatform();
    }

    static void loadAndroidPlatform() {
        put(IDisplay.class,         new AnDisplay());
        put(IDeviceInfo.class,      new AnDeviceInfo());
    }

    static void loadPlatform() {
        put(ILogManager.class,      new CommonLogManager());
        put(CommonLogWriter.class,  new CommonLogWriter());
        put(IFormFactory.class,     new AnFormFactory());
        put(MyRatings.class,        new MyRatings());
        put(ServiceProvider.class,  ServiceProvider.NULL);
        put(ServiceProviders.class, new ServiceProviders());
        put(CurrentState.class,     new CurrentState());
        put(Network.class,          new AnRawNetwork());
        StringMap stringMap = RootStringMap.of();
        put(StringMap.class,        stringMap);
        put(TaggedStringSources.class, new StringMapAsTaggedStringSources(stringMap));
        put(ItemListScreenFactoryFactory.class, new AnItemListScreenFactoryFactory());
        put(ScreenFactory.class,    RootScreenFactory.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
