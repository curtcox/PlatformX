package c1.screenfactories;

import common.screen.ScreenFactory;
import common.screenfactories.ItemListScreenFactoryFactory;

import java.util.List;

public final class C1ItemListScreenFactoryFactory
    implements ItemListScreenFactoryFactory
{
    @Override
    public ScreenFactory newFactory(List values) {
        return new C1ItemListScreenFactory(values);
    }
}
