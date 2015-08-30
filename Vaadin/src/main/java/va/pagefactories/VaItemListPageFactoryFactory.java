package va.pagefactories;

import x.event.LiveList;
import x.page.PageFactory;
import x.pagefactories.ItemListPageFactoryFactory;

public final class VaItemListPageFactoryFactory
    implements ItemListPageFactoryFactory
{
    @Override
    public PageFactory newFactory(LiveList values) {
        return new VaItemListPageFactory(values);
    }
}
