package fake;

import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import myapp.CurrentState;
import myapp.Registry;
import myapp.domain.ServiceProvider;
import myapp.services.Locations;
import myapp.stores.ServiceProviders;

/**
 *
 * @author Curt
 */
public class FakeRegistryLoader {
    
    public static void load() {
        put(Storage.class,          new FakeStorage());
        put(LocationManager.class,  new FakeLocationManager());
        put(Locations.class,        new Locations());
        put(ServiceProvider.class,  ServiceProvider.NULL);
        put(ServiceProviders.class, new ServiceProviders());
        put(CurrentState.class,     new CurrentState());
    }
    
    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
