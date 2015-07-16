package an.a22.app;

import an.a22.device.AnDeviceInfo;
import an.a22.pagefactories.AnItemListPageFactoryFactory;
import an.a22.ui.AnDisplay;
import an.a22.ui.AnFormFactory;
import an.a22.util.AnRunner;
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
import x.util.Runner;
import x.util.StringMap;

/**
 * Loads the registry with all of the instances needed for startup.
 */
final class AnRegistryLoader {

    static void load() {
        loadAndroidPlatform();
        loadPlatform();
    }

    static void loadAndroidPlatform() {
        put(IDisplay.class,         AnDisplay.of());
        put(IDeviceInfo.class,      new AnDeviceInfo());
    }

    static void loadPlatform() {
        put(ILogManager.class,      new XLogManager());
        put(XLogWriter.class,       new XLogWriter());
        put(Runner.class,           new AnRunner());
        put(IFormFactory.class,     new AnFormFactory());
        put(CurrentState.class,     new CurrentState());
        put(Network.class,          new XRawNetwork());
        StringMap stringMap = RootStringMap.of();
        put(StringMap.class,        stringMap);
        put(TaggedStringSources.class, new StringMapAsTaggedStringSources(stringMap));
        put(ItemListPageFactoryFactory.class, new AnItemListPageFactoryFactory());
        put(PageFactory.class,    RootPageFactory.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
