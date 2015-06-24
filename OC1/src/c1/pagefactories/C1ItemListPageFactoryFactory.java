package c1.pagefactories;

import common.page.PageFactory;
import common.pagefactories.ItemListPageFactoryFactory;

import java.util.List;

public final class C1ItemListPageFactoryFactory
    implements ItemListPageFactoryFactory
{
    @Override
    public PageFactory newFactory(List values) {
        return new C1ItemListPageFactory(values);
    }
}
