package ios.pagefactories;

import x.event.LiveList;
import x.page.PageFactory;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.ItemToPageLink;

public final class IosItemListPageFactoryFactory
    implements ItemListPageFactoryFactory
{
    @Override
    public PageFactory newFactory(LiveList values, ItemToPageLink itemToPageLink) {
        return new IosItemListPageFactory(values,itemToPageLink);
    }
}
