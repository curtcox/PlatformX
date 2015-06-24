package common.pages;

import common.domain.Type;
import common.page.PageLink;
import common.page.SelectionListPage;
import common.uiwidget.ISearchableList;

/**
 * For filtering provider types.
 */
public final class FilterPage
    extends SelectionListPage<Type>
{
    public FilterPage(PageLink link, ISearchableList<Type> typeList) {
        super(link,typeList);
    }

    @Override
    protected PageLink useSelectedItem(Type type) {
        return PageLink.of("Search", (Object[]) new Type[]{type});
    }
}
