package se.screenfactories;

import common.event.CommonLiveList;
import common.page.Page;
import common.page.PageFactory;
import common.page.PageLink;
import common.pagefactories.CellConfigurer;
import common.pagefactories.ItemsPage;
import common.uilist.StringToListFilter;
import common.uiwidget.ISearchableList;
import se.uilist.SESearchFilterInstaller;
import se.uilist.SESearchableList;

import javax.swing.*;
import java.util.List;

final class SEItemListPageFactory<T>
    implements PageFactory
{
    final List<T> values;

    SEItemListPageFactory(List<T> values) {
        this.values = values;
    }
    
    public Page[] create(PageLink link) {
        return new Page[] {new ItemsPage(link,newSearchableList())};
    }     

    private ISearchableList<T> newSearchableList() {
        SESearchableList<T> list = new SESearchableList(new CommonLiveList(values),new JLabel(),new CellConfigurer());
        SESearchFilterInstaller.seSpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

}
