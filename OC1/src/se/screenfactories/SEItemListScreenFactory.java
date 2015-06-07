package se.screenfactories;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screen.SelectionListScreen;
import common.uilist.IListCell;
import common.uilist.ListCellConfigurer;
import common.uilist.ListFilter;
import common.uilist.StringToListFilter;
import common.uiwidget.ISearchableList;
import common.util.Strings;
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
        SESearchFilterInstaller.seSpecificInstall(list, new TextFilter());
        return list;
    }

private static final class ItemsScreen
    extends SelectionListScreen
{
    public ItemsScreen(ScreenLink link, ISearchableList values) {
        super(link,values);
    }

    @Override
    protected ScreenLink useSelectedItem(Object item) {
        return ScreenLink.of(item.toString());
    }
}

private static final class CellConfigurer
    implements ListCellConfigurer
{
    public void configureButton(IListCell button, Object item) {
        button.setFirstRowText(item.toString());
    }
}

private static final class TextFilter
    implements StringToListFilter
{
    public ListFilter listFilterFor(final String text) {
        return new ListFilter() {
            public boolean passes(Object item) {
                String trimmed = text.trim().toLowerCase();
                return isIn(trimmed, item);
            }
        };
    }
}

private static boolean isIn(String target, Object object) {
    return object!=null && Strings.contains(object.toString().toLowerCase(), target);
}

}
