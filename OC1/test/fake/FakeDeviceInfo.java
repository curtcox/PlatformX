package fake;

import x.pagefactories.KeyValuePair;
import x.pagefactories.KeyValuePairListSource;
import x.event.LiveList;
import x.event.XLiveList;

import java.util.ArrayList;

public class FakeDeviceInfo
    implements KeyValuePairListSource
{
    @Override
    public LiveList<KeyValuePair> asKeyValuePairs() {
        return XLiveList.of(new ArrayList());
    }
}
