package x.pagefactories;

import x.page.PageLink;
import x.page.PageTags;
import x.page.SelectionListPage;
import x.uiwidget.XSearchableList;

/**
 * A page of items to be selected from.
 */
public final class ItemsPage
    extends SelectionListPage
{
    final PageTags tagsThatDescribeThisPage;
    final ItemToPageLink howMakeALinkForTheNextSelectedPage;

    public ItemsPage(PageTags tagsThatDescribeThisPage, PageLink linkUsedToGetToThisPage, XSearchableList values, ItemToPageLink howMakeALinkForTheNextSelectedPage) {
        super(linkUsedToGetToThisPage,values);
        this.tagsThatDescribeThisPage = tagsThatDescribeThisPage;
        this.howMakeALinkForTheNextSelectedPage = howMakeALinkForTheNextSelectedPage;
    }

    @Override
    protected PageLink useSelectedItem(Object item) {
        return howMakeALinkForTheNextSelectedPage.pageLink(item);
    }

    @Override
    public String toString() {
        return tagsThatDescribeThisPage.toString();
    }
}
