package c1.pagefactories;

import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.PageTags;
import x.pagefactories.ItemToPageLink;
import x.pagefactories.ItemsPage;
import x.uiwidget.XSearchableList;

import x.app.Registry;
import x.event.LiveList;

final class C1ItemListPageFactory<T>
    implements PageFactory
{
    final LiveList<T> values;
    final ItemToPageLink itemToPageLink;
    final PageTags tags;

    C1ItemListPageFactory(PageTags tags,LiveList<T> values, ItemToPageLink itemToPageLink) {
        this.tags = tags;
        this.values = values;
        this.itemToPageLink = itemToPageLink;
    }
    
    public Page[] create(PageLink link) {
        return ItemsPage.of(tags,link,newSearchableList(),itemToPageLink);
    }     

    private XSearchableList<T> newSearchableList() {
        return listBuilder().items(values).build();
    }

    XSearchableList.Builder listBuilder() {
        return Registry.get(XSearchableList.Factory.class).builder();
    }
}
