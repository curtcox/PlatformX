package fake;

import x.pagefactories.NamedValue;
import x.pagefactories.NamedValueListSource;
import x.event.LiveList;
import x.event.XLiveList;

import java.util.ArrayList;

public class FakeDeviceInfo
    implements NamedValueListSource
{
    @Override
    public LiveList<NamedValue> asNamedValues() {
        return XLiveList.of(new ArrayList());
    }
}
