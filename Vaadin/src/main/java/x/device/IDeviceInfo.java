package x.device;

import x.event.LiveList;

public interface IDeviceInfo {
    LiveList<DeviceKeyValuePair> asDeviceKeyValuePairs();
}
