package se.services;

import x.services.XLocation;
import x.services.XLocationProvider;

/**
 * Java SE implementation of LocationService.
 *
 * Sadly, there doesn't appear to be any readily available hardware or even a relevant JSR to
 * provide the level of support for Java SE that is available to Android and iOS.
 *
 * However, http://ipinfo.io/ rocks.
 *
 * For example:

  http://ipinfo.io/json

 {
     ip: "97.91.176.100",
     hostname: "97-91-176-100.dhcp.stls.mo.charter.com",
     city: "St Louis",
     region: "Missouri",
     country: "US",
     loc: "38.6657,-90.3231",
     org: "AS20115 Charter Communications",
     postal: "63130"
 }
 */
public final class SELocationProvider
    implements XLocationProvider
{
    @Override
    public XLocation getLastKnownLocation() {
        return null;
    }

    @Override
    public void setLocationListener(XLocation.Listener listener) {

    }
}
