package se.screenfactories;

import common.event.CommonLiveList;
import common.screen.Page;
import common.screen.PageFactory;
import common.screen.ScreenLink;
import common.screenfactories.CellConfigurer;
import common.screenfactories.ItemsScreen;
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
    
    public Page[] create(ScreenLink link) {
        return new Page[] {new ItemsScreen(link,newSearchableList())};
    }     

    private ISearchableList<T> newSearchableList() {
        SESearchableList<T> list = new SESearchableList(new CommonLiveList(values),new JLabel(),new CellConfigurer());
        SESearchFilterInstaller.seSpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

}
