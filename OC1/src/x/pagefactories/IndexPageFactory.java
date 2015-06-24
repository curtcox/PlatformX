package x.pagefactories;

import x.Registry;
import x.page.PageFactory;

import java.util.List;

public final class IndexPageFactory {

    public static PageFactory of(final List<String> index) {
        return factoryFactory().newFactory(index);
    }

    private static ItemListPageFactoryFactory factoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }
}
