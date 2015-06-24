package common.screenfactories;

import common.Registry;
import common.device.IDeviceInfo;
import common.page.PageFactory;

public final class DeviceInfoScreenFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory().newFactory(deviceInfo().asDeviceKeyValuePairs());
    }

    private static ItemListScreenFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListScreenFactoryFactory.class);
    }

    private static IDeviceInfo deviceInfo() {
        return Registry.get(IDeviceInfo.class);
    }
}
