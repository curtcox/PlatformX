package se.app;

import common.Registry;
import common.device.IDeviceInfo;
import common.log.CommonLogManager;
import common.log.ILogManager;
import common.log.CommonLogWriter;
import common.net.Network;
import common.page.RootPageFactory;
import common.page.PageFactory;
import common.page.dynamic.TaggedStringSources;
import common.screenfactories.ItemListScreenFactoryFactory;
import common.ui.IDisplay;
import common.ui.IFormFactory;
import common.util.StringMap;
import se.device.SEDeviceInfo;
import se.editor.ScreenEditor;
import se.events.Events;
import common.net.CommonRawNetwork;
import se.screenfactories.SEItemListScreenFactoryFactory;
import se.ui.SEDisplay;
import se.ui.SEFormFactory;
import se.util.SimpleTaggedValueStringMap;
import se.util.TaggedValueStringMap;

/**
 * Loads the registry with all of the instances needed for startup.
 */
final class SERegistryLoader {

    static void load() {
        loadStandardEditionPlatform();
        loadPlatform();
    }

    private static void loadStandardEditionPlatform() {
        put(IDeviceInfo.class,new SEDeviceInfo());
    }

    static void loadPlatform() {
        put(ILogManager.class,      new CommonLogManager());
        put(CommonLogWriter.class,  new CommonLogWriter());
        put(Events.class,           new Events());
        put(IFormFactory.class,     new SEFormFactory());
        put(IDisplay.class,         SEDisplay.of());
        put(Network.class,          new CommonRawNetwork());
        putTaggedValueStringMap();
        put(ItemListScreenFactoryFactory.class, new SEItemListScreenFactoryFactory());
        put(PageFactory.class,    RootPageFactory.of());
        put(ScreenEditor.class,     ScreenEditor.of());
    }

    private static void putTaggedValueStringMap() {
        SimpleTaggedValueStringMap stringMap = new SimpleTaggedValueStringMap();
        put(StringMap.class, stringMap);
        put(TaggedValueStringMap.class, stringMap);
        put(TaggedStringSources.class,stringMap);
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
