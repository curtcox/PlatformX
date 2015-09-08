package x.pagefactories;

import x.page.PageLink;
import x.page.SelectionListPage;
import x.uiwidget.XSearchableList;

/**
 * A page of items to be selected from.
 */
public final class ItemsPage
    extends SelectionListPage
{

    final ItemToPageLink itemToPageLink;

    public ItemsPage(PageLink link, XSearchableList values, ItemToPageLink itemToPageLink) {
        super(link,values);
        this.itemToPageLink = itemToPageLink;
    }

    @Override
    protected PageLink useSelectedItem(Object item) {
        return itemToPageLink.pageLink(item);
    }


}
