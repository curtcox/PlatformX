package c1.screenfactories;

import common.screen.ScreenFactory;
import common.screenfactories.ItemListScreenFactoryFactory;
import common.screenfactories.ListValueSupplier;

public final class C1ItemListScreenFactoryFactory
    implements ItemListScreenFactoryFactory
{
    @Override
    public ScreenFactory newFactory(ListValueSupplier supplier) {
        return new C1ItemListScreenFactory(supplier);
    }
}
