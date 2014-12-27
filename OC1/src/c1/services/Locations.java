package c1.services;

import com.codename1.location.Location;
import com.codename1.location.LocationListener;
import com.codename1.location.LocationManager;
import common.log.ILog;
import common.log.ILogManager;
import common.Registry;

public final class Locations
    implements LocationListener
{
    private Location selected;
    private Location current;
    
    public Locations() {
        LocationManager manager = Registry.get(LocationManager.class);
        manager.setLocationListener(this);
    }

    public void selectLocation(Location selected) {
        this.selected = selected;
    }

    public void locationUpdated(Location location) {
        this.current = quantize(location);
    }

    public void providerStateChanged(int newState) {}
    
    public static Locations of() {
        return Registry.get(Locations.class);
    }

    private LocationManager getManager() {
        return Registry.get(LocationManager.class);
    }

    public Location getCurrentLocation() {
        if (selected!=null) {
            return selected;
        }
        try {
            return realGetCurrentLocation();
        } catch (RuntimeException e) {
            // This can happen.  Set git for more info. 
            log(e);
            return null;
        }
    }

    private Location realGetCurrentLocation() {
        if (current!=null) {
            return current;
        }
        current = quantize(getManager().getLastKnownLocation());
        return current;
    }

    public double calculateDistance(Location a, Location b) {
        return DistanceCalculator.distance(a.getLatitude(),a.getLongitude(),b.getLatitude(),b.getLongitude());
    }

    private Location quantize(Location location) {
        return new LocationQuantizer().quantize(location);
    }

    private void log(Throwable t) {
        getLog().log(t);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(Locations.class);
    }

}
