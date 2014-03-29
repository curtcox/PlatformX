package com.mycompany.myapp.stores;

import com.codename1.location.Location;
import com.mycompany.myapp.domain.Name;
import com.mycompany.myapp.domain.Rating;
import com.mycompany.myapp.domain.ServiceProvider;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Curt
 */
public final class ServiceProviders {
    
    final static List<ServiceProvider> list = new ArrayList();
    
    static {
        list.add(newServiceProvider("Hotel 7",2.4,0.2));
        list.add(newServiceProvider("Hotel 9",2.4,0.2));
        list.add(newServiceProvider("Carlon Inn",2.4,0.2));

        list.add(newServiceProvider("What the Pho #1",2.4,0.2));
        list.add(newServiceProvider("Moe's",1.1,2.2));
        list.add(newServiceProvider("Joe's",0.1,3.1));
        list.add(newServiceProvider("Floe's",2.4,0.2));
        list.add(newServiceProvider("Pizza Palace",2.4,0.2));
        list.add(newServiceProvider("Burger Bar",2.4,0.2));
        list.add(newServiceProvider("Wok Fast",2.4,0.2));
        list.add(newServiceProvider("What the Pho #2",2.4,0.2));
    }
    
    public static List<ServiceProvider> all() {
        return list;
    }

    private static ServiceProvider newServiceProvider(String name, double lat, double lon) {
        Location location = new Location();
        location.setLatitude(lat);
        location.setLongitude(lon);
        List<Rating> ratings = new ArrayList();
        Rating myRating = new Rating("?");
        return new ServiceProvider(new Name(name),location,myRating,ratings);
    }
}
