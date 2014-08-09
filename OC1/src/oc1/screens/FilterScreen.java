package oc1.screens;

import oc1.domain.Type;
import oc1.screen.Screen;
import oc1.screen.SelectionListScreen;
import oc1.screenfactories.FilterScreenFactory;
import oc1.uilist.SearchableList;

/**
 * For filtering provider types.
 *
 * @author Curt
 */
public final class FilterScreen
    extends SelectionListScreen<Type>
{
    public FilterScreen(Screen previous,SearchableList<Type> typeList) {
        super("Filter", previous,typeList);
    }

    public static FilterScreen withPrevious(Screen previous) {
        return FilterScreenFactory.withPrevious(previous);
    }
    
    @Override
    protected void useSelectedItem(Type type) {
        SearchScreen.withPreviousAndTypes(FilterScreen.this, new Type[]{type}).show();
    }
}
