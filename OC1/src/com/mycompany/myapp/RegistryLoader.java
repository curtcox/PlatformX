package com.mycompany.myapp;

import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import com.mycompany.myapp.domain.Name;
import com.mycompany.myapp.domain.Rating;
import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.net.CachedNetwork;
import com.mycompany.myapp.net.Network;
import com.mycompany.myapp.services.Locations;
import com.mycompany.myapp.stores.MyRatings;
import com.mycompany.myapp.stores.ServiceProviders;
import com.mycompany.myapp.ui.Icons;

/**
 *
 * @author Curt
 */
final class RegistryLoader {
    
    static void load() {
        put(Network.class,          new CachedNetwork());
        put(Storage.class,          new Storage());
        put(MyRatings.class,        new MyRatings());
        put(LocationManager.class,  LocationManager.getLocationManager());
        put(Locations.class,        new Locations());
        put(ServiceProvider.class,  new ServiceProvider(null,new Name(""),null,new Rating("")));
        put(ServiceProviders.class, new ServiceProviders());
        put(CurrentState.class,     new CurrentState());
        put(Icons.class,            new Icons());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }
}
