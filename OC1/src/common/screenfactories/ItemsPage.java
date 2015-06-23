package common.screenfactories;

import common.page.PageLink;
import common.page.SelectionListPage;
import common.uiwidget.ISearchableList;

public final class ItemsPage
    extends SelectionListPage
{
    public ItemsPage(PageLink link, ISearchableList values) {
        super(link,values);
    }

    @Override
    protected PageLink useSelectedItem(Object item) {
        return PageLink.of(item.toString());
    }
}
