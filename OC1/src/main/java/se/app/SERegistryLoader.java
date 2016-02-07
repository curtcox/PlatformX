package se.app;

import se.device.SEDeviceInfo;
import se.frame.JavaSourceCodeLookup;
import se.frame.SEJavaSourceCodeLookup;
import se.views.editor.SETaggedValueEditor;
import se.event.SESwappableListFactory;
import se.events.Events;
import se.services.SELocationProvider;
import se.storage.SERamStorage;
import se.ui.SEDisplay;
import se.ui.SEFormFactory;
import se.uilist.SEListContentInstaller;
import se.uilist.SESearchFilterInstaller;
import se.uilist.SESearchableListFactory;
import se.util.SERunner;
import se.util.SimpleTaggedValueStringMap;
import se.util.TaggedValueStringMap;
import x.app.CurrentState;
import x.app.Registry;
import x.app.RootPageFactory;
import x.device.XDeviceInfo;
import x.domain.ConsumerServiceProvider;
import x.event.SwappableList;
import x.log.ILogManager;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.net.Network;
import x.net.XRawNetwork;
import x.page.PageFactory;
import x.page.dynamic.TaggedStringSources;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.XItemListPageFactoryFactory;
import x.services.XConsumerServiceProviders;
import x.services.XGeocoder;
import x.services.XLocationProvider;
import x.services.XLocationService;
import x.stores.MyRatings;
import x.stores.XStorage;
import x.ui.IDisplay;
import x.ui.IFormFactory;
import x.uilist.XListContentInstaller;
import x.uilist.XSearchFilterInstaller;
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
        put(JavaSourceCodeLookup.class,       new SEJavaSourceCodeLookup());
        put(IDisplay.class,                   SEDisplay.of());
        put(Network.class,                    new XRawNetwork());
        putTaggedValueStringMap();
        put(XSearchableList.Factory.class,    new SESearchableListFactory());
        put(XSearchFilterInstaller.class,     new SESearchFilterInstaller());
        put(XListContentInstaller.class,      new SEListContentInstaller());
        put(ConsumerServiceProvider.class,    ConsumerServiceProvider.NULL);
        put(XLocationProvider.class,          new SELocationProvider());
        put(XGeocoder.class,                  new XGeocoder());
        put(XLocationService.class,           XLocationService.create());
        put(XConsumerServiceProviders.class,  new XConsumerServiceProviders());
        put(ItemListPageFactoryFactory.class, new XItemListPageFactoryFactory());
        put(SwappableList.Factory.class,      new SESwappableListFactory());
        put(PageFactory.class,                RootPageFactory.of());
        put(XStorage.class,                   new SERamStorage());
        put(MyRatings.class,                  new MyRatings());
        put(CurrentState.class,               new CurrentState());
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
