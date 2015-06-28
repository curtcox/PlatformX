package x.pages;

import x.domain.Type;
import x.page.PageLink;
import x.page.SelectionListPage;
import x.uiwidget.XSearchableList;

/**
 * For filtering provider types.
 */
public final class FilterPage
    extends SelectionListPage<Type>
{
    public FilterPage(PageLink link, XSearchableList<Type> typeList) {
        super(link,typeList);
    }

    @Override
    protected PageLink useSelectedItem(Type type) {
        return PageLink.of("Search", (Object[]) new Type[]{type});
    }
}
