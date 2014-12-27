package c1.screenfactories;

import com.codename1.ui.Label;
import java.util.List;

import common.screen.Screen;
import common.screen.ScreenLink;
import common.screen.dynamic.GlobScreenFactory;
import c1.event.*;
import c1.screen.*;
import c1.uilist.*;
import common.util.Strings;

public abstract class AbstractItemListScreenFactory<T>
    extends GlobScreenFactory
{

    private final String glob;
    
    public AbstractItemListScreenFactory(String glob) {
        super(glob);
        this.glob = glob;
    }
    
    public Screen doCreate(ScreenLink link) {
        return new ItemScreen(newSearchableList());    
    }     

    abstract protected List<T> getValues();

    private SearchableList<T> newSearchableList() {
        SearchableList<T> list = new SearchableList(new SimpleLiveList(getValues()),new Label(),new CellConfigurer());
        SearchFilterInstaller.install(list, new TextFilter());
        return list;
    }

private final class ItemScreen
    extends SelectionListScreen
{
    public ItemScreen(SearchableList values) {
        super(glob,values);
    }

    @Override
    protected ScreenLink useSelectedItem(Object item) {
        return new ScreenLink(item.toString());
    }
}

private final class CellConfigurer
    implements ListCellConfigurer<T>
{
    public void configureButton(ListCell button, T item) {
        button.firstRow.setText(item.toString());
    }
}

private final class TextFilter
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
