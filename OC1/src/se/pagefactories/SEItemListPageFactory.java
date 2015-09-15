package se.pagefactories;

import se.uilist.SESearchFilterInstaller;
import se.uilist.SESearchableList;
import x.event.LiveList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.PageTags;
import x.pagefactories.CellConfigurer;
import x.pagefactories.ItemToPageLink;
import x.pagefactories.ItemsPage;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;

import javax.swing.*;

final class SEItemListPageFactory<T>
    implements PageFactory
{
    final LiveList<T> values;
    final ItemToPageLink itemToPageLink;
    final PageTags tags;

    SEItemListPageFactory(PageTags tags,LiveList<T> values, ItemToPageLink itemToPageLink) {
        this.tags = tags;
        this.values = values;
        this.itemToPageLink = itemToPageLink;
    }
    
    public Page[] create(PageLink link) {
        return ItemsPage.of(tags,link,newSearchableList(),itemToPageLink);
    }     

    private XSearchableList<T> newSearchableList() {
        SESearchableList<T> list = SESearchableList.of(values,new JLabel(),new CellConfigurer());
        SESearchFilterInstaller.seSpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

}
