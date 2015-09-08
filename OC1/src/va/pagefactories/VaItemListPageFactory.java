package va.pagefactories;

import com.vaadin.ui.Label;
import va.uilist.VaSearchFilterInstaller;
import va.uilist.VaSearchableList;
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

final class VaItemListPageFactory<T>
    implements PageFactory
{
    final List<T> values;
    final ItemToPageLink itemToPageLink;

    VaItemListPageFactory(List<T> values, ItemToPageLink itemToPageLink) {
        this.values = values;
        this.itemToPageLink = itemToPageLink;
    }
    
    public Page[] create(PageLink link) {
        return new Page[] {new ItemsPage(link,newSearchableList(),itemToPageLink)};
    }     

    private XSearchableList<T> newSearchableList() {
        VaSearchableList<T> list = VaSearchableList.of(new XLiveList(values),new Label(),new CellConfigurer());
        VaSearchFilterInstaller.vaSpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

}
