package com.mycompany.myapp.services;

import com.codename1.location.Location;
import com.codename1.location.LocationManager;

/**
 *
 * @author Curt
 */
public final class Locations {
    
    public static Location getCurrentLocation() {
        LocationManager locationManager = LocationManager.getLocationManager();
        return locationManager.getLastKnownLocation();
    }

    /**
     * This distance calculation is completely broken and needs to be fixed.
     * @deprecated 
     */
    public static double calculateDistance(Location a, Location b) {
        double dLat = a.getLatitude() - b.getLatitude();
        double dLon = a.getLongitude() - b.getLongitude();
        return Math.sqrt(dLat * dLat + dLon * dLon);
    }
    
}
