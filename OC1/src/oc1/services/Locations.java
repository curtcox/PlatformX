package oc1.services;

import com.codename1.location.Location;
import com.codename1.location.LocationListener;
import com.codename1.location.LocationManager;
import oc1.app.Registry;

/**
 *
 * @author Curt
 */
public final class Locations
    implements LocationListener
{

    private Location location;
    
    public Locations() {
        LocationManager manager = Registry.get(LocationManager.class);
        manager.setLocationListener(this);
    }

    public void locationUpdated(Location location) {
        this.location = quantize(location);
    }

    public void providerStateChanged(int newState) {}
    
    public static Locations of() {
        return Registry.get(Locations.class);
    }

    private LocationManager getManager() {
        return Registry.get(LocationManager.class);
    }

    public Location getCurrentLocation() {
        if (location!=null) {
            return location;
        }
        location = quantize(getManager().getLastKnownLocation());
        return location;
    }

//    public Location getCurrentLocation() {
//        return arch();
//    }
//    
//    private Location arch() {
//        Location location = new Location();
//        location.setLatitude(38.6269d);
//        location.setLongitude(-90.18697d);
//        return location;
//    }
//    
//    private Location mike() {
//        Location location = new Location();
//        location.setLatitude(40.355d);
//        location.setLongitude(-86.735d);
//        return location;
//    }
    
    public double calculateDistance(Location a, Location b) {
        return DistanceCalculator.distance(a.getLatitude(),a.getLongitude(),b.getLatitude(),b.getLongitude());
    }

    private Location quantize(Location location) {
        return new LocationQuantizer().quantize(location);
    }

}
