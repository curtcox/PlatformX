package fake;

import x.services.XLocationService;
import x.services.XLocationProvider;
import c1.ui.C1FormFactory;
import c1.ui.C1Icons;
import com.codename1.impl.ImplementationFactory;
import com.codename1.io.Storage;
import com.codename1.ui.Display;
import x.app.CurrentState;
import x.app.Registry;
import x.domain.ConsumerServiceProvider;
import x.log.ILogManager;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.XItemListPageFactoryFactory;
import x.services.XConsumerServiceProviders;
import x.ui.IDisplay;
import x.ui.IFormFactory;

public class FakeC1RegistryLoader {
    
    public static void load() {
        put(ILogManager.class,                new XLogManager());
        put(XLogWriter.class,                 new XLogWriter());
        put(Storage.class,                    new FakeStorage());
        put(XLocationProvider.class,          new FakeLocationProvider());
        put(XLocationService.class,           XLocationService.create());
        put(ConsumerServiceProvider.class,    ConsumerServiceProvider.NULL);
        put(XConsumerServiceProviders.class,  new XConsumerServiceProviders());
        put(CurrentState.class,               new CurrentState());
        put(C1Icons.class,                    new C1Icons());
        put(IFormFactory.class,               new C1FormFactory());
        put(IDisplay.class,                   new FakeDisplay());
        put(ItemListPageFactoryFactory.class, new XItemListPageFactoryFactory());
        initDisplay();
    }
    
    static void initDisplay() {
        Display.getInstance();
        ImplementationFactory.setInstance(new FakeImplementationFactory());
        Display.init(null);
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
