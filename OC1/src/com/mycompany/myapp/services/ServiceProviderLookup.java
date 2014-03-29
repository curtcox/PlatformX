package com.mycompany.myapp.services;

import com.codename1.location.Location;
import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.stores.ServiceProviders;
import java.util.List;

/**
 *
 * @author Curt
 */
public class ServiceProviderLookup {

    
    public static ServiceProvider fromCurrentLocation() {
        Location location = Locations.getCurrentLocation();
        if (location!=null) {
            return ServiceProviders.all().get(0);
        }
        
        return closestServiceProvider(location);
    }

    private static ServiceProvider closestServiceProvider(Location here) {
        List<ServiceProvider> list = ServiceProviders.all();
        ServiceProvider closest = list.get(0);
        for (ServiceProvider provider : list) {
            if (getProviderDistance(here,provider) < getProviderDistance(here,closest)) {
                closest = provider;
            }
        }
        return closest;
    }

    private static double getProviderDistance(Location here, ServiceProvider provider) {
        return Locations.calculateDistance(here,provider.location);
    }

}
