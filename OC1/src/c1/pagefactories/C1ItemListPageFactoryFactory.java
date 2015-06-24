package c1.pagefactories;

import x.page.PageFactory;
import x.pagefactories.ItemListPageFactoryFactory;

import java.util.List;

public final class C1ItemListPageFactoryFactory
    implements ItemListPageFactoryFactory
{
    @Override
    public PageFactory newFactory(List values) {
        return new C1ItemListPageFactory(values);
    }
}
