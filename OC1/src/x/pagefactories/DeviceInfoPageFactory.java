package x.pagefactories;

import x.Registry;
import x.device.XDeviceInfo;
import x.page.PageFactory;
import x.page.PageTags;
import x.event.NamedValueListSource;

public final class DeviceInfoPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory().newFactory(
                PageTags.of("Device"),
                deviceInfo().asNamedValues(),
                new NamedValueToPageLink());
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static NamedValueListSource deviceInfo() {
        return Registry.get(XDeviceInfo.class);
    }

}
