package fake;

import c1.services.C1Locations;
import c1.services.ILocationManager;
import c1.ui.C1FormFactory;
import c1.ui.C1Icons;
import com.codename1.impl.ImplementationFactory;
import com.codename1.io.Storage;
import com.codename1.ui.Display;
import x.Registry;
import x.app.CurrentState;
import x.domain.ServiceProvider;
import x.log.XLogManager;
import x.log.ILogManager;
import x.log.XLogWriter;
import x.services.ServiceProviders;
import x.ui.IDisplay;
import x.ui.IFormFactory;

public class FakeC1RegistryLoader {
    
    public static void load() {
        put(ILogManager.class,      new XLogManager());
        put(XLogWriter.class,        new XLogWriter());
        put(Storage.class,          new FakeStorage());
        put(ILocationManager.class, new FakeLocationManager());
        put(C1Locations.class,        new C1Locations());
        put(ServiceProvider.class,  ServiceProvider.NULL);
        put(ServiceProviders.class, new ServiceProviders());
        put(CurrentState.class,     new CurrentState());
        put(C1Icons.class,            new C1Icons());
        put(IFormFactory.class,     new C1FormFactory());
        put(IDisplay.class,         new FakeDisplay());
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
