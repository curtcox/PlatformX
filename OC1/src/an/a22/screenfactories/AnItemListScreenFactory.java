package an.a22.screenfactories;

import an.a22.uilist.AnSearchFilterInstaller;
import an.a22.uilist.AnSearchableList;
import android.content.Context;
import android.widget.TextView;
import common.Registry;
import common.event.CommonLiveList;
import common.screen.Page;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screenfactories.CellConfigurer;
import common.screenfactories.ItemsScreen;
import common.uilist.StringToListFilter;
import common.uiwidget.ISearchableList;

import java.util.List;

final class AnItemListScreenFactory<T>
    implements ScreenFactory
{
    final List<T> values;

    AnItemListScreenFactory(List<T> values) {
        this.values = values;
    }
    
    public Page[] create(ScreenLink link) {
        return new Page[] {new ItemsScreen(link,newSearchableList())};
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
