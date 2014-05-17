package com.mycompany.myapp;

import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import com.mycompany.myapp.domain.Name;
import com.mycompany.myapp.domain.Rating;
import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.services.Locations;
import com.mycompany.myapp.stores.ServiceProviders;

/**
 *
 * @author Curt
 */
final class RegistryLoader {
    
    static void load() {
        put(Storage.class,          new Storage());
        put(LocationManager.class,  LocationManager.getLocationManager());
        put(Locations.class,        new Locations());
        put(ServiceProvider.class,  new ServiceProvider(null,new Name(""),null,new Rating("")));
        put(ServiceProviders.class, new ServiceProviders());
        put(CurrentState.class,     new CurrentState());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }
}
