package common.screens;

import common.domain.Type;
import common.screen.ScreenLink;
import common.screen.SelectionListScreen;
import common.uiwidget.ISearchableList;

/**
 * For filtering provider types.
 */
public final class FilterScreen
    extends SelectionListScreen<Type>
{
    public FilterScreen(ScreenLink link, ISearchableList<Type> typeList) {
        super(link,typeList);
    }

    @Override
    protected ScreenLink useSelectedItem(Type type) {
        return ScreenLink.of("Search",(Object[])new Type[]{type});
    }
}
