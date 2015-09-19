package fake;

import x.device.XDeviceInfo;
import x.event.LiveList;
import x.event.XLiveList;
import x.util.NamedValue;

import java.util.ArrayList;

public class FakeDeviceInfo
    implements XDeviceInfo
{
    @Override
    public LiveList<NamedValue> asNamedValues() {
        return XLiveList.of(new ArrayList());
    }
}
