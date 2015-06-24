package an.a22.pagefactories;

import x.page.PageFactory;
import x.pagefactories.ItemListPageFactoryFactory;

import java.util.List;

public final class AnItemListPageFactoryFactory
    implements ItemListPageFactoryFactory
{
    @Override
    public PageFactory newFactory(List values) {
        return new AnItemListPageFactory(values);
    }
}
