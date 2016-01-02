package x.services;

import x.app.Registry;

public final class XLocationService
    implements XLocation.Listener
{
    private XLocation selected;
    private XLocation current;

    private XLocationService() {}

    public static XLocationService create() {
        XLocationService service = new XLocationService();
        getManager().setLocationListener(service);
        return service;
    }

    public void selectLocation(XLocation selected) {
        this.selected = selected;
    }

    public void onChange(XLocation location) {
        this.current = quantize(location);
    }

    public static XLocationService of() {
        return Registry.get(XLocationService.class);
    }

    private static XLocationProvider getManager() {
        return Registry.get(XLocationProvider.class);
    }

    public XLocation getCurrentLocation() {
        if (selected!=null) {
            return selected;
        }
        return realGetCurrentLocation();
    }

    private XLocation realGetCurrentLocation() {
        if (current!=null) {
            return current;
        }
        current = quantize(getManager().getLastKnownLocation());
        return current;
    }

    private XLocation quantize(XLocation location) {
        return new XLocationQuantizer().quantize(location);
    }

}
