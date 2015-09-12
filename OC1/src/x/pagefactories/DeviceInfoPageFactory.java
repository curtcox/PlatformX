package x.pagefactories;

import x.Registry;
import x.page.PageFactory;
import x.page.PageTags;

public final class DeviceInfoPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory().newFactory(
                PageTags.of("Device"),
                deviceInfo().asKeyValuePairs(),
                new KeyValuePairToPageLink());
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static KeyValuePairListSource deviceInfo() {
        return Registry.get(KeyValuePairListSource.class);
    }

}
