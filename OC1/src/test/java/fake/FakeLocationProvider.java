package fake;

import x.services.XLocation;
import x.services.XLocationProvider;

public class FakeLocationProvider
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
