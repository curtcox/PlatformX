package an.a22.pagefactories;

import x.event.LiveList;
import x.page.PageFactory;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.ItemToPageLink;

public final class AnItemListPageFactoryFactory
    implements ItemListPageFactoryFactory
{
    @Override
    public PageFactory newFactory(LiveList values, ItemToPageLink itemToPageLink) {
        return new AnItemListPageFactory(values, itemToPageLink);
    }
}
