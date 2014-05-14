package com.mycompany.myapp;

import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import com.mycompany.myapp.services.Locations;
import com.mycompany.myapp.stores.ServiceProviders;

/**
 *
 * @author Curt
 */
final class RegistryLoader {
    
    static void load() {
        Registry.put(Storage.class,          new Storage());
        Registry.put(LocationManager.class,  LocationManager.getLocationManager());
        Registry.put(Locations.class,        new Locations());
        Registry.put(ServiceProviders.class, new ServiceProviders());
        Registry.put(CurrentState.class,     new CurrentState());
    }

}
