package x.pagefactories;

import x.Registry;
import x.page.PageFactory;

public final class RegistryInfoPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory()
                .newFactory(registryInfo().asKeyValuePairs(),new KeyValuePairToPageLink());
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static KeyValuePairListSource registryInfo() {
        return Registry.get(Registry.class);
    }

}
