package c1.services;

import com.codename1.location.Location;
import com.codename1.location.LocationListener;

public final class LocationManager
    implements ILocationManager
{
    @Override
    public Location getLastKnownLocation() {
        return c1LocationManager().getLastKnownLocation();
    }

    @Override
    public void setLocationListener(LocationListener listener) {
        c1LocationManager().setLocationListener(listener);
    }

    private com.codename1.location.LocationManager c1LocationManager() {
        return com.codename1.location.LocationManager.getLocationManager();
    }
}
