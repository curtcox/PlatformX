package x.pagefactories;

import x.Registry;
import x.device.IDeviceInfo;
import x.page.PageFactory;

public final class DeviceInfoPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory().newFactory(deviceInfo().asDeviceKeyValuePairs());
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static IDeviceInfo deviceInfo() {
        return Registry.get(IDeviceInfo.class);
    }
}
