package ios.pagefactories;

import ios.uilist.IosSearchFilterInstaller;
import ios.uilist.IosSearchableList;
import ios.uiwidget.IosLabelViewController;
import x.event.XLiveList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.pagefactories.CellConfigurer;
import x.pagefactories.ItemsPage;
import x.uilist.StringToListFilter;
import x.uiwidget.XLabel;
import x.uiwidget.XSearchableList;

import java.util.List;

final class IosItemListPageFactory<T>
    implements PageFactory
{
    final List<T> values;

    IosItemListPageFactory(List<T> values) {
        this.values = values;
    }
    
    public Page[] create(PageLink link) {
        return new Page[] {new ItemsPage(link,newSearchableList())};
    }     

    private XSearchableList<T> newSearchableList() {
        IosSearchableList<T> list = IosSearchableList.of(new XLiveList(values), IosLabelViewController.of(new XLabel()),new CellConfigurer());
        IosSearchFilterInstaller.iosSpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

}
