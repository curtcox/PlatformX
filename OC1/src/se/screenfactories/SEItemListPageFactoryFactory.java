package se.screenfactories;

import common.page.PageFactory;
import common.pagefactories.ItemListPageFactoryFactory;

import java.util.List;

public final class SEItemListPageFactoryFactory
    implements ItemListPageFactoryFactory
{
    @Override
    public PageFactory newFactory(List values) {
        return new SEItemListPageFactory(values);
    }
}
