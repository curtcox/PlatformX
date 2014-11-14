package oc2.screens;

import oc1.domain.Type;
import oc1.screen.ScreenLink;
import oc1.screen.SelectionListScreen;
import oc1.uilist.SearchableList;

/**
 * For filtering provider types.
 *
 * @author Curt
 */
public final class FilterScreen
    extends SelectionListScreen<Type>
{
    public FilterScreen(SearchableList<Type> typeList) {
        super("Filter",typeList);
    }

    @Override
    protected ScreenLink useSelectedItem(Type type) {
        return new ScreenLink("Search",(Object[])new Type[]{type});
    }
}
