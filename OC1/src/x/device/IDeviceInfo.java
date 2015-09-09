package x.device;

import x.event.LiveList;
import x.pagefactories.KeyValuePair;

public interface IDeviceInfo {
    LiveList<KeyValuePair> asDeviceKeyValuePairs();
}
