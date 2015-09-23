package x.services;

/**
 * This currently just rounds latitude and longitude to the nearest 0.001,
 * but it could easily do something more polar friendly.
 * @author Curt
 */
final class XLocationQuantizer {

    XLocation quantize(XLocation location) {
        return new XLocation(round(location.latitude),round(location.longitude));
    }

    private static double round(double x) {
        return Math.round(x*1000.0)/1000.0;
    }

}
