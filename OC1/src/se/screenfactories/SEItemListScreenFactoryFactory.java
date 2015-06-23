package se.screenfactories;

import common.screen.PageFactory;
import common.screenfactories.ItemListScreenFactoryFactory;

import java.util.List;

public final class SEItemListScreenFactoryFactory
    implements ItemListScreenFactoryFactory
{
    @Override
    public PageFactory newFactory(List values) {
        return new SEItemListPageFactory(values);
    }
}
