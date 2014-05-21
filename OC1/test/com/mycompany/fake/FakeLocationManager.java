package com.mycompany.fake;

import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import java.io.IOException;

/**
 *
 * @author Curt
 */
public class FakeLocationManager 
    extends LocationManager
{

    @Override
    public Location getCurrentLocation() throws IOException {
        return new Location();
    }

    @Override
    public Location getLastKnownLocation() {
        return new Location();
    }

    @Override
    protected void bindListener() {
    }

    @Override
    protected void clearListener() {
        throw never();
    }

    private RuntimeException never() {
        throw new RuntimeException("Not supported yet.");
    }
    
}
