package c1.screenfactories;

import c1.uilist.C1SearchFilterInstaller;
import c1.uilist.C1SearchableList;
import com.codename1.ui.Label;
import common.event.CommonLiveList;
import common.page.Page;
import common.screen.PageFactory;
import common.page.ScreenLink;
import common.screenfactories.CellConfigurer;
import common.screenfactories.ItemsPage;
import common.uilist.StringToListFilter;
import common.uiwidget.ISearchableList;

import java.util.List;

final class C1ItemListPageFactory<T>
    implements PageFactory
{
    final List<T> values;

    C1ItemListPageFactory(List<T> values) {
        this.values = values;
    }
    
    public Page[] create(ScreenLink link) {
        return new Page[] {new ItemsPage(link,newSearchableList())};
    }     

    private ISearchableList<T> newSearchableList() {
        C1SearchableList<T> list = new C1SearchableList(new CommonLiveList(values),new Label(),new CellConfigurer());
        C1SearchFilterInstaller.c1SpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

}
