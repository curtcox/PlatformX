package x.domain;

import x.services.XLocation;

public final class LocationDescription {

    public final String address;
    private final Double latitude;
    private final Double longitude;
    
    public LocationDescription(String address, Double latitude, Double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public XLocation toLocation() {
        return new XLocation(latitude,longitude);
    }

    @Override
    public String toString() {
        return 
               " address=" + address +
               " latitude=" + latitude + 
               " longitude=" + longitude
       ; 
    }
    
}
