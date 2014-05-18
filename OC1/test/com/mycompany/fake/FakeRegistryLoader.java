package com.mycompany.fake;

import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import com.mycompany.myapp.CurrentState;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.services.Locations;
import com.mycompany.myapp.stores.ServiceProviders;

/**
 *
 * @author Curt
 */
public class FakeRegistryLoader {
    
    public static void load() {
        put(Storage.class, new FakeStorage());
        put(LocationManager.class, new FakeLocationManager());
        put(Locations.class, new Locations());
        put(ServiceProviders.class, new ServiceProviders());
        put(CurrentState.class, new CurrentState());
    }
    
    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
