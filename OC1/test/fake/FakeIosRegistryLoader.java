package fake;

import ios.pagefactories.IosItemListPageFactoryFactory;
import ios.ui.IosFormFactory;
import x.Registry;
import x.app.CurrentState;
import x.domain.ServiceProvider;
import x.log.ILogManager;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.pagefactories.ItemListPageFactoryFactory;
import x.services.ServiceProviders;
import x.ui.IDisplay;
import x.ui.IFormFactory;

public class FakeIosRegistryLoader {
    
    public static void load() {
        put(ILogManager.class,                new XLogManager());
        put(XLogWriter.class,                 new XLogWriter());
        put(ServiceProvider.class,            ServiceProvider.NULL);
        put(ServiceProviders.class,           new ServiceProviders());
        put(CurrentState.class, new CurrentState());
        put(IFormFactory.class,               new IosFormFactory());
        put(IDisplay.class,                   new FakeDisplay());
        put(ItemListPageFactoryFactory.class, new IosItemListPageFactoryFactory());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
