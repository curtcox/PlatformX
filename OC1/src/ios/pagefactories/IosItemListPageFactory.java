package ios.pagefactories;

import ios.uilist.IosSearchFilterInstaller;
import ios.uilist.IosSearchableList;
import org.robovm.apple.uikit.UILabel;
import x.event.XLiveList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.pagefactories.CellConfigurer;
import x.pagefactories.ItemsPage;
import x.uilist.StringToListFilter;
import x.uiwidget.ISearchableList;

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

    private ISearchableList<T> newSearchableList() {
        IosSearchableList<T> list = new IosSearchableList(new XLiveList(values),new UILabel(),new CellConfigurer());
        IosSearchFilterInstaller.anSpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

}
