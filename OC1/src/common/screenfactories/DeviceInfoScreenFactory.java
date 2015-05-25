package common.screenfactories;

import c1.device.DeviceInfo;
import c1.screenfactories.ItemListScreenFactory;
import common.screen.ScreenFactory;

public final class DeviceInfoScreenFactory {

    public static ScreenFactory of() {
        return new ItemListScreenFactory(new ListValueSupplier(DeviceInfo.asDeviceKeyValuePairs()));
    }
}
