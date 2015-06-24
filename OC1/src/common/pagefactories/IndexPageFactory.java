package common.pagefactories;

import common.Registry;
import common.page.PageFactory;

import java.util.List;

public final class IndexPageFactory {

    public static PageFactory of(final List<String> index) {
        return factoryFactory().newFactory(index);
    }

    private static ItemListPageFactoryFactory factoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }
}
