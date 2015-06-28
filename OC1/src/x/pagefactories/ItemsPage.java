package x.pagefactories;

import x.page.PageLink;
import x.page.SelectionListPage;
import x.uiwidget.XSearchableList;

public final class ItemsPage
    extends SelectionListPage
{
    public ItemsPage(PageLink link, XSearchableList values) {
        super(link,values);
    }

    @Override
    protected PageLink useSelectedItem(Object item) {
        return PageLink.of(item.toString());
    }
}
