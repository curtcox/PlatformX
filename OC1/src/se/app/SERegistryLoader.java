package se.app;

import se.device.SEDeviceInfo;
import se.editor.ScreenEditor;
import se.events.Events;
import x.pagefactories.XItemListPageFactoryFactory;
import se.ui.SEDisplay;
import se.ui.SEFormFactory;
import se.uilist.SESearchableListFactory;
import se.util.SERunner;
import se.util.SimpleTaggedValueStringMap;
import se.util.TaggedValueStringMap;
import x.app.Registry;
import x.device.XDeviceInfo;
import x.log.ILogManager;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.net.Network;
import x.net.XRawNetwork;
import x.page.PageFactory;
import x.app.RootPageFactory;
import x.page.dynamic.TaggedStringSources;
import x.pagefactories.ItemListPageFactoryFactory;
import x.ui.IDisplay;
import x.ui.IFormFactory;
import x.uiwidget.XSearchableList;
import x.util.Runner;
import x.util.StringMap;

/**
 * Loads the registry with all of the instances needed for startup.
 */
final class SERegistryLoader {

    static void load() {
        loadStandardEditionPlatform();
        loadPlatform();
    }

    private static void loadStandardEditionPlatform() {
        put(XDeviceInfo.class,new SEDeviceInfo());
    }

    static void loadPlatform() {
        put(ILogManager.class,      new XLogManager());
        put(XLogWriter.class,       new XLogWriter());
        put(Events.class,           new Events());
        put(Runner.class,           new SERunner());
        put(IFormFactory.class,     new SEFormFactory());
        put(IDisplay.class,         SEDisplay.of());
        put(Network.class,          new XRawNetwork());
        putTaggedValueStringMap();
        put(XSearchableList.Factory.class,    new SESearchableListFactory());
        put(ItemListPageFactoryFactory.class, new XItemListPageFactoryFactory());
        put(PageFactory.class,      RootPageFactory.of());
        put(ScreenEditor.class,     ScreenEditor.of());
    }

    private static void putTaggedValueStringMap() {
        SimpleTaggedValueStringMap stringMap = new SimpleTaggedValueStringMap();
        put(StringMap.class,            stringMap);
        put(TaggedValueStringMap.class, stringMap);
        put(TaggedStringSources.class,  stringMap);
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
