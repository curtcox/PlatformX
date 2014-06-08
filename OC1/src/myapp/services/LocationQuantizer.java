package myapp.services;

import com.codename1.location.Location;

/**
 * This currently just rounds latitude and longitude to the nearest 0.001,
 * but it could easily do something more polar friendly.
 * @author Curt
 */
final class LocationQuantizer {

    Location quantize(Location location) {
        Location quantized = new Location();
        quantized.setLatitude(round(location.getLatitude()));
        quantized.setLongitude(round(location.getLongitude()));
        return quantized;
    }

    private static double round(double x) {
        return Math.round(x*1000.0)/1000.0;
    }

}
