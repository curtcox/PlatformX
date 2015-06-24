package se.pagefactories;

import x.page.PageFactory;
import x.pagefactories.ItemListPageFactoryFactory;

import java.util.List;

public final class SEItemListPageFactoryFactory
    implements ItemListPageFactoryFactory
{
    @Override
    public PageFactory newFactory(List values) {
        return new SEItemListPageFactory(values);
    }
}
