package c1.services;

import com.codename1.location.Location;
import com.codename1.location.LocationListener;
import x.services.XLocation;
import x.services.XLocationProvider;

public final class C1LocationProvider
    implements XLocationProvider
{
    @Override
    public XLocation getLastKnownLocation() {
        return convert(c1LocationManager().getLastKnownLocation());
    }

    private XLocation convert(Location lastKnownLocation) {
        return null;
    }

    @Override
    public void setLocationListener(XLocation.Listener listener) {
        c1LocationManager().setLocationListener(convert(listener));
    }

    private LocationListener convert(XLocation.Listener listener) {
        return null;
    }

    private com.codename1.location.LocationManager c1LocationManager() {
        return com.codename1.location.LocationManager.getLocationManager();
    }
}
