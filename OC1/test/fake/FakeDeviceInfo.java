package fake;

import x.device.DeviceKeyValuePair;
import x.device.IDeviceInfo;

import java.util.ArrayList;
import java.util.List;

public class FakeDeviceInfo
    implements IDeviceInfo
{
    @Override
    public List<DeviceKeyValuePair> asDeviceKeyValuePairs() {
        return new ArrayList<DeviceKeyValuePair>();
    }
}
