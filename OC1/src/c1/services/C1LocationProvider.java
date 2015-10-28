package c1.services;

import com.codename1.location.Location;
import com.codename1.location.LocationListener;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.services.XLocation;
import x.services.XLocationProvider;

public final class C1LocationProvider
    implements XLocationProvider
{
    @Override
    public XLocation getLastKnownLocation() {
        try {
            return convert(c1LocationManager().getLastKnownLocation());
        } catch (RuntimeException e) {
            // This can happen.  Set git for more info.
            log(e);
            return null;
        }
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

    private void log(Throwable t) {
        getLog().log(t);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(C1LocationProvider.class,this);
    }

}
