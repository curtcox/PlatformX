package c1.pagefactories;

import c1.uilist.C1SearchFilterInstaller;
import c1.uilist.C1SearchableList;
import com.codename1.ui.Label;
import x.event.CommonLiveList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.pagefactories.CellConfigurer;
import x.pagefactories.ItemsPage;
import x.uilist.StringToListFilter;
import x.uiwidget.ISearchableList;

import java.util.List;

final class C1ItemListPageFactory<T>
    implements PageFactory
{
    final List<T> values;

    C1ItemListPageFactory(List<T> values) {
        this.values = values;
    }
    
    public Page[] create(PageLink link) {
        return new Page[] {new ItemsPage(link,newSearchableList())};
    }     

    private ISearchableList<T> newSearchableList() {
        C1SearchableList<T> list = new C1SearchableList(new CommonLiveList(values),new Label(),new CellConfigurer());
        C1SearchFilterInstaller.c1SpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

}
