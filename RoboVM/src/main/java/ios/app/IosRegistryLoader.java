package ios.app;

import ios.device.IosDeviceInfo;
import ios.pagefactories.IosItemListPageFactoryFactory;
import ios.ui.IosDisplay;
import ios.ui.IosFormFactory;
import x.Registry;
import x.app.CurrentState;
import x.device.IDeviceInfo;
import x.log.ILogManager;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.net.Network;
import x.net.RootStringMap;
import x.net.XRawNetwork;
import x.page.PageFactory;
import x.page.RootPageFactory;
import x.page.dynamic.StringMapAsTaggedStringSources;
import x.page.dynamic.TaggedStringSources;
import x.pagefactories.ItemListPageFactoryFactory;
import x.ui.IDisplay;
import x.ui.IFormFactory;
import x.util.StringMap;

/**
 * Loads the registry with all of the instances needed for startup.
 */
final class IosRegistryLoader {

    static void load() {
        loadIosPlatform();
        loadPlatform();
    }

    static void loadIosPlatform() {
        put(IDisplay.class,         IosDisplay.of());
        put(IDeviceInfo.class,      new IosDeviceInfo());
    }

    static void loadPlatform() {
        put(ILogManager.class,      new XLogManager());
        put(XLogWriter.class,       new XLogWriter());
        put(IFormFactory.class,     new IosFormFactory());
        put(CurrentState.class,     new CurrentState());
        put(Network.class,          new XRawNetwork());
        StringMap stringMap = RootStringMap.of();
        put(StringMap.class,        stringMap);
        put(TaggedStringSources.class,        new StringMapAsTaggedStringSources(stringMap));
        put(ItemListPageFactoryFactory.class, new IosItemListPageFactoryFactory());
        put(PageFactory.class,    RootPageFactory.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
