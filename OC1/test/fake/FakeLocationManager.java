package fake;

import c1.services.ILocationManager;
import com.codename1.location.Location;
import com.codename1.location.LocationListener;

public class FakeLocationManager
    implements ILocationManager
{

    @Override
    public Location getLastKnownLocation() {
        return null;
    }

    @Override
    public void setLocationListener(LocationListener listener) {

    }
}
