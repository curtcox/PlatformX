package fake;

import c1.services.ILocationManager;
import com.codename1.impl.ImplementationFactory;
import com.codename1.io.Storage;
import com.codename1.ui.Display;
import common.log.ILogManager;
import common.app.CurrentState;
import common.Registry;
import common.domain.ServiceProvider;
import c1.log.LogManager;
import c1.log.LogWriter;
import c1.services.C1Locations;
import common.services.ServiceProviders;
import c1.ui.C1FormFactory;
import common.ui.IDisplay;
import common.ui.IFormFactory;
import c1.ui.Icons;

public class FakeC1RegistryLoader {
    
    public static void load() {
        put(ILogManager.class,      new LogManager());
        put(LogWriter.class,        new LogWriter());
        put(Storage.class,          new FakeStorage());
        put(ILocationManager.class, new FakeLocationManager());
        put(C1Locations.class,        new C1Locations());
        put(ServiceProvider.class,  ServiceProvider.NULL);
        put(ServiceProviders.class, new ServiceProviders());
        put(CurrentState.class,     new CurrentState());
        put(Icons.class,            new Icons());
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
