package fake;

import x.device.DeviceKeyValuePair;
import x.device.IDeviceInfo;
import x.event.LiveList;
import x.event.XLiveList;

public class FakeDeviceInfo
    implements IDeviceInfo
{
    @Override
    public LiveList<DeviceKeyValuePair> asDeviceKeyValuePairs() {
        return new XLiveList();
    }
}
