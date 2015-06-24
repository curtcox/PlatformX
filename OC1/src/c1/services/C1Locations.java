package c1.services;

import com.codename1.location.Location;
import com.codename1.location.LocationListener;
import x.log.ILog;
import x.log.ILogManager;
import x.Registry;
import x.services.LocationReading;
import x.services.LocationService;

public final class C1Locations
    implements LocationService, LocationListener
{
    private Location selected;
    private Location current;
    
    public C1Locations() {
        ILocationManager manager = Registry.get(ILocationManager.class);
        manager.setLocationListener(this);
    }

    public void selectLocation(LocationReading selected) {
        this.selected = convert(selected);
    }

    private Location convert(LocationReading selected) {
        Location location = new Location();
        return location;
    }

    public void locationUpdated(Location location) {
        this.current = quantize(location);
    }

    public void providerStateChanged(int newState) {}
    
    public static C1Locations of() {
        return Registry.get(C1Locations.class);
    }

    private ILocationManager getManager() {
        return Registry.get(ILocationManager.class);
    }

    public LocationReading getCurrentLocation() {
        if (selected!=null) {
            return convert(selected);
        }
        try {
            return convert(realGetCurrentLocation());
        } catch (RuntimeException e) {
            // This can happen.  Set git for more info. 
            log(e);
            return null;
        }
    }

    @Override
    public int calculateDistance(LocationReading location, LocationReading currentLocation) {
        return 0;
    }

    private LocationReading convert(Location selected) {
        return null;
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
        return Registry.get(ILogManager.class).getLog(C1Locations.class);
    }

}
