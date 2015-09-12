package an.a22.pagefactories;

import x.event.LiveList;
import x.page.PageFactory;
import x.page.PageTags;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.ItemToPageLink;

public final class AnItemListPageFactoryFactory
    implements ItemListPageFactoryFactory
{
    @Override
    public PageFactory newFactory(PageTags tags,LiveList values, ItemToPageLink itemToPageLink) {
        return new AnItemListPageFactory(tags,values, itemToPageLink);
    }
}
