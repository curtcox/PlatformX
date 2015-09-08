package ios.pagefactories;

import ios.uilist.IosSearchFilterInstaller;
import ios.uilist.IosSearchableList;
import ios.uiwidget.IosLabelViewController;
import x.Registry;
import x.event.XLiveList;
import x.log.ILog;
import x.log.ILogManager;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.pagefactories.CellConfigurer;
import x.pagefactories.ItemToPageLink;
import x.pagefactories.ItemsPage;
import x.uilist.StringToListFilter;
import x.uiwidget.XLabel;
import x.uiwidget.XSearchableList;

import java.util.List;

final class IosItemListPageFactory<T>
    implements PageFactory
{
    final List<T> values;
    final ItemToPageLink itemToPageLink;

    IosItemListPageFactory(List<T> values, ItemToPageLink itemToPageLink) {
        this.values = values;
        this.itemToPageLink = itemToPageLink;
    }
    
    public Page[] create(PageLink link) {
        return new Page[] {new ItemsPage(link,newSearchableList(),itemToPageLink)};
    }     

    private XSearchableList<T> newSearchableList() {
        IosSearchableList<T> list = IosSearchableList.of(new XLiveList(values), IosLabelViewController.of(new XLabel()),new CellConfigurer());
        IosSearchFilterInstaller.iosSpecificInstall(list, StringToListFilter.DEFAULT);
        log(" created " + list);
        return list;
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IosItemListPageFactory.class);
    }
}
