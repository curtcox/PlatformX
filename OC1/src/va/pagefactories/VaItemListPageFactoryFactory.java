package va.pagefactories;

import x.event.LiveList;
import x.page.PageFactory;
import x.page.PageTags;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.ItemToPageLink;

public final class VaItemListPageFactoryFactory
    implements ItemListPageFactoryFactory
{
    @Override
    public PageFactory newFactory(PageTags tags, LiveList values, ItemToPageLink itemToPageLink) {
        return new VaItemListPageFactory(tags,values,itemToPageLink);
    }
}
