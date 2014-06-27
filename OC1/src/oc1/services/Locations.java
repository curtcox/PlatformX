package oc1.services;

import com.codename1.location.Location;
import com.codename1.location.LocationListener;
import com.codename1.location.LocationManager;
import oc1.app.Registry;
import oc1.log.LogManager;

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

// Guard against the following encountered running
//    on Samsung SPH-M830
//    on Android 4.1.2
//    on 2014/06/26
//D/OC1     ( 8161): [EDT] 0:0:0,0 - Exception: java.lang.IllegalArgumentException - provider==null
//W/System.err( 8161): java.lang.IllegalArgumentException: provider==null
//W/System.err( 8161):    at android.location.LocationManager.getLastKnownLocation(LocationManager.java:1185)
//W/System.err( 8161):    at com.codename1.impl.android.AndroidLocationManager.getCurrentLocation(AndroidLocationManager.java:106)
//W/System.err( 8161):    at com.codename1.impl.android.AndroidLocationManager.getLastKnownLocation(AndroidLocationManager.java:248)
//W/System.err( 8161):    at oc1.services.Locations.getCurrentLocation(Locations.java:41)
    public Location getCurrentLocation() {
        try {
            return realGetCurrentLocation();
        } catch (RuntimeException e) {
            log(e);
            return null;
        }
    }

    private Location realGetCurrentLocation() {
        if (location!=null) {
            return location;
        }
        location = quantize(getManager().getLastKnownLocation());
        return location;
    }

//    public Location getCurrentLocation() {
//        return arch();
//    }
    
    private Location arch() {
        Location location = new Location();
        location.setLatitude(38.6269d);
        location.setLongitude(-90.18697d);
        return location;
    }
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

    private void log(Throwable t) {
        LogManager.of().getLog(Locations.class).log(t);    
    }

}
