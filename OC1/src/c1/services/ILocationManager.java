package c1.services;

import com.codename1.location.Location;
import com.codename1.location.LocationListener;

public interface ILocationManager {
    Location getLastKnownLocation();
    void setLocationListener(LocationListener listener);
}
