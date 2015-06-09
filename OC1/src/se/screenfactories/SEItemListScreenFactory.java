package se.screenfactories;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screenfactories.CellConfigurer;
import common.screenfactories.ItemsScreen;
import common.uilist.StringToListFilter;
import common.uiwidget.ISearchableList;
import se.event.SELiveList;
import se.uilist.SESearchFilterInstaller;
import se.uilist.SESearchableList;

import javax.swing.*;
import java.util.List;

final class SEItemListScreenFactory<T>
    implements ScreenFactory
{
    final List<T> values;

    SEItemListScreenFactory(List<T> values) {
        this.values = values;
    }
    
    public Screen[] create(ScreenLink link) {
        return new Screen[] {new ItemsScreen(link,newSearchableList())};
    }     

    private ISearchableList<T> newSearchableList() {
        SESearchableList<T> list = new SESearchableList(new SELiveList(values),new JLabel(),new CellConfigurer());
        SESearchFilterInstaller.seSpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }

}
