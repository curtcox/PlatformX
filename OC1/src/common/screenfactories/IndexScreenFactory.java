package common.screenfactories;

import common.Registry;
import common.screen.PageFactory;

import java.util.List;

public final class IndexScreenFactory {

    public static PageFactory of(final List<String> index) {
        return factoryFactory().newFactory(index);
    }

    private static ItemListScreenFactoryFactory factoryFactory() {
        return Registry.get(ItemListScreenFactoryFactory.class);
    }
}
