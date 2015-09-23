package x.services;

import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;

public final class XLocations
    implements XLocation.Listener
{
    private XLocation selected;
    private XLocation current;

    public XLocations() {
        XLocationProvider manager = Registry.get(XLocationProvider.class);
        manager.setLocationListener(this);
    }

    public void selectLocation(XLocation selected) {
        this.selected = selected;
    }

    public void onChange(XLocation location) {
        this.current = quantize(location);
    }

    public void providerStateChanged(int newState) {}
    
    public static XLocations of() {
        return Registry.get(XLocations.class);
    }

    private XLocationProvider getManager() {
        return Registry.get(XLocationProvider.class);
    }

    public XLocation getCurrentLocation() {
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

    private XLocation realGetCurrentLocation() {
        if (current!=null) {
            return current;
        }
        current = quantize(getManager().getLastKnownLocation());
        return current;
    }

    private XLocation quantize(XLocation location) {
        return new LocationQuantizer().quantize(location);
    }

    private void log(Throwable t) {
        getLog().log(t);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(XLocations.class);
    }

}
