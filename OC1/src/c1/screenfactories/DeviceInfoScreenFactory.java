package c1.screenfactories;

import c1.device.DeviceInfo;
import common.screen.ScreenFactory;

public final class DeviceInfoScreenFactory {

    public static ScreenFactory of() {
        return new AbstractItemListScreenFactory(new ListValueSupplier(DeviceInfo.asDeviceKeyValuePairs()));
    }
}
