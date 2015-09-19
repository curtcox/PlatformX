package x.specificpagefactories;

import x.app.Registry;
import x.event.NamedValueListSource;
import x.page.PageFactory;
import x.page.PageTags;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.NamedValuePageFactory;

public final class RegistryInfoPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory().newFactory(
            PageTags.of("Registry"),
            registryInfo().asNamedValues(),
            NamedValuePageFactory.ITEM_TO_PAGELINK);
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static NamedValueListSource registryInfo() {
        return Registry.get(Registry.class);
    }

}
