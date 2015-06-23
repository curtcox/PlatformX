package common.screens;

import common.domain.Type;
import common.page.ScreenLink;
import common.page.SelectionListPage;
import common.uiwidget.ISearchableList;

/**
 * For filtering provider types.
 */
public final class FilterPage
    extends SelectionListPage<Type>
{
    public FilterPage(ScreenLink link, ISearchableList<Type> typeList) {
        super(link,typeList);
    }

    @Override
    protected ScreenLink useSelectedItem(Type type) {
        return ScreenLink.of("Search",(Object[])new Type[]{type});
    }
}
