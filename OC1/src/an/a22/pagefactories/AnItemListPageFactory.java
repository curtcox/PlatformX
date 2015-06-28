package an.a22.pagefactories;

import an.a22.uilist.AnSearchFilterInstaller;
import an.a22.uilist.AnSearchableList;
import android.content.Context;
import android.widget.TextView;
import x.Registry;
import x.event.XLiveList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.pagefactories.CellConfigurer;
import x.pagefactories.ItemsPage;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;

import java.util.List;

final class AnItemListPageFactory<T>
    implements PageFactory
{
    final List<T> values;

    AnItemListPageFactory(List<T> values) {
        this.values = values;
    }
    
    public Page[] create(PageLink link) {
        return new Page[] {new ItemsPage(link,newSearchableList())};
    }     

    private XSearchableList<T> newSearchableList() {
        AnSearchableList<T> list = new AnSearchableList(new XLiveList(values),new TextView(context()),new CellConfigurer());
        AnSearchFilterInstaller.anSpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

    private Context context() {
        return Registry.get(Context.class);
    }

}
