package c1.pagefactories;

import c1.uilist.C1SearchFilterInstaller;
import c1.uilist.C1SearchableList;
import com.codename1.ui.Label;
import x.event.XLiveList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.pagefactories.CellConfigurer;
import x.pagefactories.ItemToPageLink;
import x.pagefactories.ItemsPage;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;

import java.util.List;

final class C1ItemListPageFactory<T>
    implements PageFactory
{
    final List<T> values;
    final ItemToPageLink itemToPageLink;

    C1ItemListPageFactory(List<T> values, ItemToPageLink itemToPageLink) {
        this.values = values;
        this.itemToPageLink = itemToPageLink;
    }
    
    public Page[] create(PageLink link) {
        return new Page[] {new ItemsPage(link,newSearchableList(),itemToPageLink)};
    }     

    private XSearchableList<T> newSearchableList() {
        C1SearchableList<T> list = new C1SearchableList(new XLiveList(values),new Label(),new CellConfigurer());
        C1SearchFilterInstaller.c1SpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

}
