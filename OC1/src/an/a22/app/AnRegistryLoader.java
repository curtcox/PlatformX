package an.a22.app;

import an.a22.device.AnDeviceInfo;
import an.a22.screenfactories.AnItemListPageFactoryFactory;
import an.a22.ui.AnDisplay;
import an.a22.ui.AnFormFactory;
import common.Registry;
import common.app.CurrentState;
import common.device.IDeviceInfo;
import common.log.CommonLogManager;
import common.log.CommonLogWriter;
import common.log.ILogManager;
import common.net.CommonRawNetwork;
import common.net.Network;
import common.net.RootStringMap;
import common.page.PageFactory;
import common.page.RootPageFactory;
import common.page.dynamic.StringMapAsTaggedStringSources;
import common.page.dynamic.TaggedStringSources;
import common.pagefactories.ItemListPageFactoryFactory;
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
        put(CurrentState.class,     new CurrentState());
        put(Network.class,          new CommonRawNetwork());
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
