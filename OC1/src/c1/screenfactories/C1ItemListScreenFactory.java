package c1.screenfactories;

import com.codename1.ui.Label;
import java.util.List;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import c1.event.*;
import c1.uilist.*;
import common.screen.SelectionListScreen;
import common.uilist.ListFilter;
import common.uilist.StringToListFilter;
import common.uiwidget.ISearchableList;
import common.util.Strings;

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
        SearchableList<T> list = new SearchableList(new SimpleLiveList(values),new Label(),new CellConfigurer());
        SearchFilterInstaller.install(list, new TextFilter());
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
    public void configureButton(ListCell button, Object item) {
        button.firstRow.setText(item.toString());
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
