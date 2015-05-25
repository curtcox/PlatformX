package common.screenfactories;

import common.screen.ScreenFactory;

public interface ItemListScreenFactoryFactory {
    ScreenFactory newFactory(ListValueSupplier supplier);
}
