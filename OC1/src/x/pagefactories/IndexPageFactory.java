package x.pagefactories;

import x.Registry;
import x.event.LiveList;
import x.page.PageFactory;

public final class IndexPageFactory {

    public static PageFactory of(final LiveList<String> index) {
        return factoryFactory().newFactory(index,ItemToPageLink.TO_STRING);
    }

    private static ItemListPageFactoryFactory factoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }
}
