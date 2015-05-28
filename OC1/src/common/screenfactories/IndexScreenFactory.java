package common.screenfactories;

import common.Registry;
import common.screen.ScreenFactory;
import common.screenfactories.ItemListScreenFactoryFactory;

import java.util.List;

public final class IndexScreenFactory {

    public static ScreenFactory of(final List<String> index) {
        return factoryFactory().newFactory(index);
    }

    private static ItemListScreenFactoryFactory factoryFactory() {
        return Registry.get(ItemListScreenFactoryFactory.class);
    }
}
