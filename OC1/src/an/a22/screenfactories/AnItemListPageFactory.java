package an.a22.screenfactories;

import an.a22.uilist.AnSearchFilterInstaller;
import an.a22.uilist.AnSearchableList;
import android.content.Context;
import android.widget.TextView;
import common.Registry;
import common.event.CommonLiveList;
import common.page.Page;
import common.screen.PageFactory;
import common.page.PageLink;
import common.screenfactories.CellConfigurer;
import common.screenfactories.ItemsPage;
import common.uilist.StringToListFilter;
import common.uiwidget.ISearchableList;

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

    private ISearchableList<T> newSearchableList() {
        AnSearchableList<T> list = new AnSearchableList(new CommonLiveList(values),new TextView(context()),new CellConfigurer());
        AnSearchFilterInstaller.anSpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

    private Context context() {
        return Registry.get(Context.class);
    }

}
