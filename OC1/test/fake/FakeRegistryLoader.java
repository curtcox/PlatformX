package fake;

import com.codename1.impl.ImplementationFactory;
import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import com.codename1.ui.Display;
import oc1.app.CurrentState;
import oc1.app.Registry;
import oc1.domain.ServiceProvider;
import oc1.log.LogManager;
import oc1.log.LogWriter;
import oc1.services.Locations;
import oc1.services.ServiceProviders;
import oc1.ui.C1FormFactory;
import oc1.ui.IDisplay;
import oc1.ui.IFormFactory;
import oc1.ui.Icons;

public class FakeRegistryLoader {
    
    public static void load() {
        put(LogManager.class,       new LogManager());
        put(LogWriter.class,        new LogWriter());
        put(Storage.class,          new FakeStorage());
        put(LocationManager.class,  new FakeLocationManager());
        put(Locations.class,        new Locations());
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
