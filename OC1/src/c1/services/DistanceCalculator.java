package c1.services;

final class DistanceCalculator {

    static double distance(double lat1, double lon1, double lat2, double lon2) {
      double theta = lon1 - lon2;
      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
      dist = acos(dist);
      dist = rad2deg(dist);
      dist = dist * 60 * 1.1515;
      return (dist);
    }

    /**
     * This function converts decimal degrees to radians 
     */
    private static double deg2rad(double deg) {
      return (deg * Math.PI / 180.0);
    }

    /**
     * This function converts radians to decimal degrees
     */
    private static double rad2deg(double rad) {
      return (rad * 180.0 / Math.PI);
    }

    /**
     * Returns arccos x.  That is return y such that x = cos y.
     * This is provided, because it isn't available on JME.
     */
    static double acos(double x) {
        final double epsilon=1.0E-60;
        double lo =  0;
        double hi =  Math.PI;
        double y = 0;
        for (int i=0; i<30; i++) {
            y = (lo + hi) / 2;
            double cosy = Math.cos(y);
            double delta = Math.abs(cosy - x); 
            if (delta<epsilon || hi - lo <epsilon) {
                return y;
            }
            if (cosy>x) {
                lo = y;
            } else {
                hi = y;
            }
        }
        return y;
    }

}
