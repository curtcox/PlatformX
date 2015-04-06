package c1.screens;

import common.domain.Type;
import common.screen.ScreenLink;
import c1.screen.SelectionListScreen;
import c1.uilist.SearchableList;

/**
 * For filtering provider types.
 */
public final class FilterScreen
    extends SelectionListScreen<Type>
{
    public FilterScreen(ScreenLink link, SearchableList<Type> typeList) {
        super(link,typeList);
    }

    @Override
    protected ScreenLink useSelectedItem(Type type) {
        return ScreenLink.of("Search",(Object[])new Type[]{type});
    }
}
