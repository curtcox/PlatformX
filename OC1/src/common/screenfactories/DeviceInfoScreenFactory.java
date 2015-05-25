package common.screenfactories;

import common.Registry;
import common.device.IDeviceInfo;
import common.screen.ScreenFactory;

public final class DeviceInfoScreenFactory {

    public static ScreenFactory of() {
        return itemListScreenFactoryFactory().newFactory(new ListValueSupplier(deviceInfo().asDeviceKeyValuePairs()));
    }

    private static ItemListScreenFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListScreenFactoryFactory.class);
    }

    private static IDeviceInfo deviceInfo() {
        return Registry.get(IDeviceInfo.class);
    }
}
