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

    final Locations locations = Locations.of();
    final ServiceProviders serviceProviders = ServiceProviders.of();
    
    public ServiceProvider fromCurrentLocation() {
        Location location = locations.getCurrentLocation();
        if (location!=null) {
            return serviceProviders.all().get(0);
        }
        
        return closestServiceProvider(location);
    }

    private ServiceProvider closestServiceProvider(Location here) {
        List<ServiceProvider> list = serviceProviders.all();
        ServiceProvider closest = list.get(0);
        for (ServiceProvider provider : list) {
            if (getProviderDistance(here,provider) < getProviderDistance(here,closest)) {
                closest = provider;
            }
        }
        return closest;
    }

    private double getProviderDistance(Location here, ServiceProvider provider) {
        return locations.calculateDistance(here,provider.location);
    }

}
