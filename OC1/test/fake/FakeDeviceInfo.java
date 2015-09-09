package fake;

import x.pagefactories.KeyValuePair;
import x.device.IDeviceInfo;
import x.event.LiveList;
import x.event.XLiveList;

public class FakeDeviceInfo
    implements IDeviceInfo
{
    @Override
    public LiveList<KeyValuePair> asDeviceKeyValuePairs() {
        return new XLiveList();
    }
}
