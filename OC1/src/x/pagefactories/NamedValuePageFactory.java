package x.pagefactories;

import x.Registry;
import x.page.PageFactory;
import x.page.PageTags;

public final class NamedValuePageFactory {


    public static PageFactory of() {
        return itemListScreenFactoryFactory().newFactory(
                PageTags.of("NamedValue"),
                deviceInfo().asNamedValues(),
                new NamedValueToPageLink());
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static NamedValueListSource deviceInfo() {
        return Registry.get(NamedValueListSource.class);
    }

}
