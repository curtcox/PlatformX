package se.screenfactories;

import common.screen.ScreenFactory;
import common.screenfactories.ItemListScreenFactoryFactory;

import java.util.List;

public final class SEItemListScreenFactoryFactory
    implements ItemListScreenFactoryFactory
{
    @Override
    public ScreenFactory newFactory(List values) {
        return new SEItemListScreenFactory(values);
    }
}
