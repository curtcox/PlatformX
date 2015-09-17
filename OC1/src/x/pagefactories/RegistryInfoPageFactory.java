package x.pagefactories;

import x.Registry;
import x.page.PageFactory;
import x.page.PageTags;

public final class RegistryInfoPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory().newFactory(
            PageTags.of("Registry"),
            registryInfo().asNamedValues(),
            new NamedValueToPageLink());
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static NamedValueListSource registryInfo() {
        return Registry.get(Registry.class);
    }

}
