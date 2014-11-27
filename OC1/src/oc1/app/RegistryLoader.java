package oc1.app;

import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import com.codename1.ui.Display;
import oc1.domain.ServiceProvider;
import oc1.log.*;
import oc1.net.*;
import oc1.screen.*;
import oc2.screen.*;
import oc1.services.*;
import oc1.ui.*;
import oc1.util.StringMap;
import oc2.net.SimpleNetStringMap;
import oc2.stores.MyRatings;

/**
 * Loads the registry with all of the instances needed for startup.
 * @author Curt
 */
final class RegistryLoader {
    
    static void load() {
        put(ExceptionLogger.class,  new ExceptionLogger());
        put(LogManager.class,       new LogManager());
        put(LogWriter.class,        new LogWriter());
        put(FormFactory.class,      new FormFactory());
        put(Display.class,          Display.getInstance());
        put(Storage.class,          new Storage());
        put(Network.class,          new CachedNetwork());
        put(MyRatings.class,        new MyRatings());
        put(LocationManager.class,  LocationManager.getLocationManager());
        put(Locations.class,        new Locations());
        put(ServiceProvider.class,  ServiceProvider.NULL);
        put(ServiceProviders.class, new ServiceProviders());
        put(Geocoder.class,         new Geocoder());
        put(CurrentState.class,     new CurrentState());
        put(Icons.class,            new Icons());
        put(StringMap.class,        new SimpleNetStringMap(URIs.URI("http://localhost:8000/")));
        put(ScreenFactory.class,    RootScreenFactory.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }
}
