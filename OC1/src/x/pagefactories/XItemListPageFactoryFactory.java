package x.pagefactories;

import x.event.LiveList;
import x.page.PageFactory;
import x.page.PageTags;

public final class XItemListPageFactoryFactory
    implements ItemListPageFactoryFactory
{
    @Override
    public PageFactory newFactory(PageTags tags,LiveList values, ItemToPageLink itemToPageLink) {
        return new XItemListPageFactory(tags,values,itemToPageLink);
    }
}
