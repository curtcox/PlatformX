package c1.screenfactories;

import com.codename1.ui.Label;
import java.util.List;

import common.event.CommonLiveList;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import c1.uilist.*;
import common.screenfactories.CellConfigurer;
import common.screenfactories.ItemsScreen;
import common.uilist.*;
import common.uiwidget.ISearchableList;

final class C1ItemListScreenFactory<T>
    implements ScreenFactory
{
    final List<T> values;

    C1ItemListScreenFactory(List<T> values) {
        this.values = values;
    }
    
    public Screen[] create(ScreenLink link) {
        return new Screen[] {new ItemsScreen(link,newSearchableList())};
    }     

    private ISearchableList<T> newSearchableList() {
        C1SearchableList<T> list = new C1SearchableList(new CommonLiveList(values),new Label(),new CellConfigurer());
        C1SearchFilterInstaller.c1SpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

}
