package oc1.app;

import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import com.codename1.ui.Display;
import oc1.domain.ServiceProvider;
import oc1.log.LogManager;
import oc1.log.LogWriter;
import oc1.net.CachedNetwork;
import oc1.net.Network;
import oc2.screen.OysterCrackerScreenFactory;
import oc1.screen.ScreenFactory;
import oc1.services.Geocoder;
import oc1.services.Locations;
import oc1.services.ServiceProviders;
import oc1.ui.FormFactory;
import oc1.ui.Icons;
import oc2.stores.MyRatings;

/**
 *
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
        put(ScreenFactory.class,    OysterCrackerScreenFactory.of());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }
}
