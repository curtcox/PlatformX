package fake;

import x.pagefactories.KeyValuePair;
import x.device.KeyValuePairListSource;
import x.event.LiveList;
import x.event.XLiveList;

public class FakeDeviceInfo
    implements KeyValuePairListSource
{
    @Override
    public LiveList<KeyValuePair> asKeyValuePairs() {
        return new XLiveList();
    }
}
