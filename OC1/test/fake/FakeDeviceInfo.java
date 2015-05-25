package fake;

import common.device.DeviceKeyValuePair;
import common.device.IDeviceInfo;

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
