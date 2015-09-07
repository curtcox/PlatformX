package x.pagefactories;

import x.page.Page;
import x.page.PageLink;
import x.page.SelectionListPage;
import x.uiwidget.XSearchableList;

/**
 * A page of items to be selected from.
 */
public final class ItemsPage
    extends SelectionListPage
{
    public ItemsPage(PageLink link, XSearchableList values) {
        super(link,values);
    }

    @Override
    protected PageLink useSelectedItem(Object item) {
        if (item instanceof Page) {
            return useSelectedPage((Page) item);
        }
        return PageLink.of(item.toString());
    }

    private PageLink useSelectedPage(Page page) {
        return PageLink.of(page,page.toString());
    }

}
