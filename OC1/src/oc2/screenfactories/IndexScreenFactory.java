package oc2.screenfactories;

import com.codename1.ui.Label;
import oc1.event.*;
import oc1.screen.*;
import oc1.uilist.*;
import oc1.util.Strings;
import oc2.screen.RootScreenFactory;

public final class IndexScreenFactory 
    extends AbstractItemListScreenFactory
{

    public IndexScreenFactory() {
        super("Index");
    }

    public Screen doCreate(ScreenLink link) {
        return new IndexScreen(newSearchableList());    
    }     

    private static LiveList<String> getValues() {
        return new SimpleLiveList(RootScreenFactory.index);
    }

    private static SearchableList<String> newSearchableList() {
        SearchableList list = new SearchableList(getValues(),new Label(),new CellConfigurer());
        SearchFilterInstaller.install(list, new TextFilter());
        return list;
    }

static final class IndexScreen
    extends SelectionListScreen<String>
{
    public IndexScreen(SearchableList<String> values) {
        super("Index",values);
    }

    @Override
    protected ScreenLink useSelectedItem(String item) {
        return new ScreenLink(item);
    }
}

static final class CellConfigurer
    implements ListCellConfigurer<String>
{
    public void configureButton(ListCell button, String string) {
        button.firstRow.setText(string);
    }
}

static final class TextFilter
    implements StringToListFilter
{

    public ListFilter listFilterFor(final String text) {
        return new ListFilter() {
            public boolean passes(Object item) {
                String trimmed = text.trim().toLowerCase();
                String string = (String) item;
                return isIn(trimmed, string);
            }
        };
    }

    private static boolean isIn(String target, Object object) {
        return object!=null && Strings.contains(object.toString().toLowerCase(), target);
    }
}

}
