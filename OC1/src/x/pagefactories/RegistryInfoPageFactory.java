package x.pagefactories;

import x.Registry;
import x.device.KeyValuePairListSource;
import x.page.PageFactory;

public final class RegistryInfoPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory()
                .newFactory(deviceInfo().asKeyValuePairs(),new KeyValuePairToPageLink());
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static KeyValuePairListSource deviceInfo() {
        return Registry.get(KeyValuePairListSource.class);
    }

}
