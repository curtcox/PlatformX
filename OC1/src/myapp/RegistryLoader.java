package myapp;

import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import com.codename1.ui.Display;
import myapp.domain.ServiceProvider;
import myapp.log.LogManager;
import myapp.log.LogWriter;
import myapp.net.CachedNetwork;
import myapp.net.Network;
import myapp.services.Locations;
import myapp.stores.MyRatings;
import myapp.stores.ServiceProviders;
import myapp.ui.Icons;

/**
 *
 * @author Curt
 */
final class RegistryLoader {
    
    static void load() {
        put(LogManager.class,       new LogManager());
        put(LogWriter.class,        new LogWriter());
        put(Display.class,          Display.getInstance());
        put(Storage.class,          new Storage());
        put(Network.class,          new CachedNetwork());
        put(MyRatings.class,        new MyRatings());
        put(LocationManager.class,  LocationManager.getLocationManager());
        put(Locations.class,        new Locations());
        put(ServiceProvider.class,  ServiceProvider.NULL);
        put(ServiceProviders.class, new ServiceProviders());
        put(CurrentState.class,     new CurrentState());
        put(Icons.class,            new Icons());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }
}
